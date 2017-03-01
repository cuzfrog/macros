package com.github.cuzfrog.macros.codegen

/**
  * Created by cuz on 3/1/17.
  */
object DynamicMixinTest extends App {


  val instance = new TextInput("ID", "NAME")

  val mixed = new TextInput(instance.id, instance.name) with Control

  import Dynamix._

  val dynamixed = instance._with[Control]()

  class A extends TextInput("","asdfa") with Control

  new A
}
