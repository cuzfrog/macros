package com.github.cuzfrog.macros.implicittest


/**
  * Created by cuz on 1/19/17.
  */
object ImplicitMacroTest extends App{
  def show[T](x: T)(implicit s: Showable[T]): String = s.show(x)
  println(show(2))
  println(show("2s"))
  println(show(2.0))
  println(show(Item(5)))
}

case class Item(id:Int)
