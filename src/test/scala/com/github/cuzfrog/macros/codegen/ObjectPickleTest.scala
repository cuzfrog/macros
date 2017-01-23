package com.github.cuzfrog.macros.codegen

import java.io.{FileOutputStream, ObjectOutputStream}
import java.nio.file.{Files, Paths}

/**
  * Created by cuz on 1/23/17.
  */
object ObjectPickleTest extends App {
  val src = SourceReader.readSource("Func1")
  Files.write(Paths.get("/tmp/func.obj"), src.getBytes)
}
