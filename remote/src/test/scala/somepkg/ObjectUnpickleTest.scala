package somepkg

import java.io.{FileInputStream, ObjectInputStream}
import java.nio.file.{Files, Paths}

import com.github.cuzfrog.macros.RuntimeCompiler

/**
  * Created by cuzfrog on 1/22/17.
  */
object ObjectUnpickleTest extends App{
  val src=scala.io.Source.fromFile("/tmp/func.obj").mkString

  val func=RuntimeCompiler.compileLogic(src)
  println(func("some"))
  println(func("<html>contents sasdf</html>"))
}
