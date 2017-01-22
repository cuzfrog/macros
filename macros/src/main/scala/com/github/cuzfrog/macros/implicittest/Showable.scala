package com.github.cuzfrog.macros.implicittest

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
/**
  * This is a test for implicit macros.
  * Created by cuz on 1/19/17.
  */
trait Showable[T] {def show(x: T): String}

private object Showable {
  implicit def materializeShowable[T]: Showable[T] = macro provideInstance[T]

  def provideInstance[T: c.WeakTypeTag](c: blackbox.Context): c.universe.Tree = {
    import c.universe._
    val tpe=c.weakTypeOf[T]
    q"""
       new Showable[$tpe]{
         def show(x:$tpe)=x.toString
       }
     """
  }
}
