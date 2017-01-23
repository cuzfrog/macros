package com.github.cuzfrog.macros.codegen

import java.io.InputStream

/**
  * Created by cuz on 1/23/17.
  */
object SourceReader {
  def readSource(name: String): String = {
    val stream: InputStream = getClass.getResourceAsStream("/" + name + ".scala")
    scala.io.Source.fromInputStream(stream).mkString
  }
}
