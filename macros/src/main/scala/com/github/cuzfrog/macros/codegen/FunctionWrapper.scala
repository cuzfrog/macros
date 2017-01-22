package com.github.cuzfrog.macros.codegen

import scala.language.experimental.macros
import scala.reflect.macros.blackbox


object FunctionWrapper {
  def apply[P, R](fn: P => R): Function[P, R] = macro FunctionMacroImpl.apply_impl[P, R]
}

private object FunctionMacroImpl{
  def apply_impl[P: c.WeakTypeTag, R: c.WeakTypeTag](c: blackbox.Context)(fn: c.Expr[P => R]): c.Tree = {
    import c.universe._
    val tree=q"$fn"
    println(show(tree))
    println(showCode(tree))
    tree
  }
}

/*

class FunctionWrapper[P, R](val fn: P => R, description: String)
  extends Function1[P, R] {
  def apply(p: P) = fn(p)
  override def toString = description
}
*/
