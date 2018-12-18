package com.tdtk.scala

object VariableDemo {


  def main(args: Array[String]) {
    //使用val定义的变量值是不可变的，相当于java里用final修饰的变量
    val i = 1
    //使用var定义的变量是可变得，在Scala中鼓励使用val
    var s = "hello"
    //Scala编译器会自动推断变量的类型，必要的时候可以指定类型
    //变量名在前，类型在后
    val str: String = "scala 是一个函数式编程的开发语言"

    // Scala 多个变量声明
    val xmax, ymax = 100 // xmax, ymax都声明为100
    // val 来声明一个元组
    val pa = (40, "Foo")

    //字符串插值 格式：s"${变量}"
    val myname="chenfeng"
    val stri= s"My name is ${myname}"

    println(i)
    println(s)
    s = "world"
    // i=2
    println(s)
    println(str)
    println(xmax)
    println(ymax)
    println(pa)
    println(stri)
  }

}
