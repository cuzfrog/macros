package com.github.cuzfrog.macros.codegen

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

/**
  * Created by cuz on 3/1/17.
  */
object Dynamix {
  type MTool[C, T] = com.github.cuzfrog.macros.codegen.MixinTool[C, T]

  implicit class ExCaseClass[C](in: C) {
    def _with[T]()(implicit tool: MTool[C, T]): C with T = tool.dynamix(in)
  }

}


trait MixinTool[C, T] {
  def dynamix(in: C): C with T
}

object MixinTool {
  implicit def materialize[C, T]: MixinTool[C, T] = macro MacroImpl.impl[C, T]
}

private object MacroImpl {
  def impl[C: c.WeakTypeTag, T: c.WeakTypeTag](c: whitebox.Context): c.Tree = {
    def echo(msg: String) = c.echo(c.enclosingPosition, msg)
    def error(msg: String) = c.error(c.enclosingPosition, msg)
    import c.universe._
    val tpeC = c.weakTypeOf[C]
    val tpeT = c.weakTypeOf[T]

    val params = tpeC.member(termNames.CONSTRUCTOR).asMethod.paramLists.head

    val constrParamsDef = params.map { p =>
      val n = p.asTerm.name.decodedName.toTermName
      q"override val $n : ${p.info}"
    }
    val extendsParamsDef = params.map(p => q"$p")

    val constrBody: List[c.universe.Tree] = {
      params.map { param =>
        q"in $param"
      }
    }

    val MixinToolSym = typeOf[MixinTool[_, _]].typeSymbol
    val freshName = TypeName(c.freshName)

//    val classDef =
//      q"""
//         package ${TermName(tpeC.typeSymbol.owner.fullName)} {
//           case class $freshName (..$constrParamsDef)
//                   extends $tpeC (..$extendsParamsDef) with $tpeT
//         }
//       """

    val classDef =q"""
                     class $freshName (..$constrParamsDef)
                             extends $tpeC (..$extendsParamsDef) with $tpeT
                   """
    val tree = List(
      q"""
       new $MixinToolSym[$tpeC,$tpeT]{
           def dynamix(in: $tpeC): $tpeC with $tpeT = {
              new $tpeC (..$constrBody) with $tpeT
           }
       }
     """)

    val tree2 = q"new $tpeC(..$constrBody) with $tpeT"
    echo("-------------------------")
    echo(showCode(q"..$tree"))
    q"..$tree"
  }
}

