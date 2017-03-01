package com.github.cuzfrog.macros.codegen

/**
  * Created by cuz on 3/1/17.
  */
trait Field{
  def value:String = ???

}

trait Control extends Field{
  override def value: String = "I have a value."
}

class TextInput(val id:String,val name:String) extends Field

