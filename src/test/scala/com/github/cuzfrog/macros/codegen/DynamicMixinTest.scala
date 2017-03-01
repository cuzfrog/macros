package com.github.cuzfrog.macros.codegen

/**
  * Created by cuz on 3/1/17.
  */
object DynamicMixinTest {


  val instance = new TextInput("ID", "NAME")

  val mixed = new TextInput(instance.id, instance.name) with Control

  import Dynamix._

  val mt=MixinTool.materialize[TextInput,Control]

  val dynamixed = mt.dynamix(instance)

  class A extends TextInput("","asdfa") with Control

  new A

  def main(args:Array[String]):Unit={
    //println(dynamixed.`value`)
  }
}
