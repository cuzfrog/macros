package com.github.cuzfrog.macros.codegen

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

/**
  * Created by cuz on 3/1/17.
  */
object Dynamix {
  type MTool[C, T] = MixinTool[C, T]

  implicit class ExCaseClass[C](in: C) {
    def _with[T]()(implicit tool: MixinTool[C, T]): C with T = tool.dynamix(in)
  }

}


trait MixinTool[C, T] {
  def dynamix(in: C): C with T
}

private object MixinTool {
  implicit def materialize[C, T]: MixinTool[C, T] = macro MacroImpl.impl[C, T]
}

private object MacroImpl {
  def impl[C: c.WeakTypeTag, T: c.WeakTypeTag](c: blackbox.Context): c.Tree = {
    import c.universe._
    val tpeC = c.weakTypeOf[C]
    val tpeT = c.weakTypeOf[T]
    val symbol = tpeT.typeSymbol

    val params = tpeC.member(termNames.CONSTRUCTOR).asMethod.paramLists.head

    val constrParamsDef = params.map{ p =>
      val n = p.asTerm.name.decodedName.toTermName
      q"override val $n : ${p.info }"
    }
    val extendsParamsDef = params.map(p => q"$p")
    println(constrParamsDef)
    val constrBody: List[c.universe.Tree] = {
      params.map { param =>
        q"in $param"
      }
    }


    val MixinToolSym = typeOf[MixinTool[_, _]].typeSymbol
    val freshName = c.freshName(typeNames.EMPTY)
    val tree =
      q"""
       case class $freshName (..$constrParamsDef)
                   extends $tpeC (..$extendsParamsDef) with $tpeT

       new $MixinToolSym[$tpeC,$tpeT]{
           def dynamix(in: $tpeC): $tpeC with $tpeT = {
              new $tpeC (..$constrBody) with $tpeT
           }
       }
     """
    println("-------------------------")
    println(showCode(tree))

    tree
  }
}

