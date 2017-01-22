package com.github.cuzfrog.macros.codegen

import scala.language.experimental.macros
import scala.reflect.macros.blackbox


object SourceCode {
  def apply[P, R](fn: P => R): String = macro SourceCodeMacroImpl.apply_impl[P, R]
}

private object SourceCodeMacroImpl{
  def apply_impl[P, R](c: blackbox.Context)(fn: c.Expr[P => R]): c.Tree = {
    import c.universe._
    val tree=q"$fn"
    println(s"Compilation:$tree")
    q"${showCode(tree)}"
    //showCode get the source code and quasiqoute lift the string to tree as return
  }
}