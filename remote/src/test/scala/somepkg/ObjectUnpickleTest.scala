package somepkg

import java.io.{FileInputStream, ObjectInputStream}

/**
  * Created by cuzfrog on 1/22/17.
  */
object ObjectUnpickleTest extends App{
  val is = new ObjectInputStream(new FileInputStream("/tmp/test.obj"))
  val func2 = is.readObject().asInstanceOf[Function[String, String]]

  println(func2("some"))
}
