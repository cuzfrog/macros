package com.github.cuzfrog

import com.github.cuzfrog.macros.codegen.ClassRegister

/**
  * Created by cuz on 1/19/17.
  */
object FunctionReifyTest {
  ClassRegister.feifyFunc[MyLogic]
}

class MyLogic extends Function[String,String]{
  override def apply(in: String): String = in + "123"
}