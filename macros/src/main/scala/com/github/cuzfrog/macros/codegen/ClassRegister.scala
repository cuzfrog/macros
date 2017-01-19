package com.github.cuzfrog.macros.codegen

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

/**
  * Created by cuz on 1/19/17.
  */
object ClassRegister {
  def feifyFunc[T <: Function[String, String]]: Function[String, String] = macro impl[T]

  def impl[T: c.WeakTypeTag](c: blackbox.Context): c.universe.Tree ={
    import c.universe._
    val tpe=c.weakTypeOf[T]
    val q"""$tree""" = tpe.typeSymbol
    println(showRaw(tree))

    //q"new Function[String,String]{$logicDef}"
    ???
  }
}
