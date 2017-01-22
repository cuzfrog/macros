package com.github.cuzfrog.macros.codegen

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}
import java.nio.ByteBuffer

import thirdparty.{AnotherLogic, Person}


/**
  * Created by cuz on 1/19/17.
  */
object FunctionReifyTest extends App {


  val func1 = (s: String) =>
    s match {
      case "1" => "one"
      case other => other + "(other)"
    }

  println(func1("sasome"))

  //
  //  val func2 = SourceCode {
  //    (p: Person) =>
  //      p match {
  //        case Person(age) => age
  //        case other => 0
  //      }
  //  }
  //  println(func2)
}


