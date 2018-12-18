package com.tdtk.scala


/**
  *
  * Scala 方法是类的一部分，而函数是一个对象可以赋值给一个变量。
  * 换句话来说在类中定义的函数即是方法。
  *
  * val 定义函数
  * def 定义方法
  *
  * 方法声明：
  * def functionName ([参数列表]) : [return type]
  * 如果你不写等于号和方法主体，那么方法会被隐式声明为抽象(abstract)，包含它的类型于是也是一个抽象类型。
  *
  *
  * 方法定义
  * 方法定义由一个 def 关键字开始，紧接着是可选的参数列表，一个冒号 : 和方法的返回类型，一个等于号 = ，最后是方法的主体。
  * Scala 方法定义格式如下：
  * def functionName ([参数列表]) : [return type] = {
  * function body
  * return [expr]
  * }
  *
  * 以上代码中 return type 可以是任意合法的 Scala 数据类型。参数列表中的参数可以使用逗号分隔。
  * 以下方法的功能是将两个传入的参数相加并求和：
  * object add{
  * def addInt( a:Int, b:Int ) : Int = {
  * var sum:Int = 0
  * sum = a + b
  * return sum
  * }
  * }
  *
  * 如果方法没有返回值，可以返回为 Unit，这个类似于 Java 的 void, 实例如下：
  * object Hello{
  * def printMe( ) : Unit = {
  * println("Hello, Scala!")
  * }
  * }
  *
  * 方法的返回值类型可以不写，编译器可以自动推断出来，但是对于递归函数，必须指定返回类型
  *
  *
  * 方法调用
  * 标准格式：functionName( 参数列表 )
  * 也可以使用类似java的格式(使用.号)：[instance.]functionName( 参数列表 )
  *
  * 在Scala中无法直接操作方法，如果要操作方法，必须先将其转换成函数。有
  * 两种方法可以将方法转换成函数：
  * 第一种：val f1 = m _
  * 如果直接传递的是方法名称，scala相当于是把方法转成了函数(隐式转换)
  * 通过x => m0(x)的方式将方法转化成函数,这个函数是一个匿名函数，等价：(x:Int) => m0(x)
  *
  * Scala中的+ - * / %等操作符的作用与Java一样，位操作符 & | ^ >> <<也一样。只是有
  * 一点特别的：这些操作符实际上是方法。例如：
  * a + b 简写为 a.+(b)
  * a方法b 可以写成 a.方法(b)
  *
  *
  *
  */
class MethodAndFunction {

  //定义一个方法
  //方法m2参数要求是一个函数，函数的参数必须是两个Int类型
  //返回值类型也是Int类型
  def m1(f: (Int, Int) => Int) : Int = {
    f(2, 6)
  }

  //定义一个函数f1，参数是两个Int类型，返回值是一个Int类型
  val f1 = (x: Int, y: Int) => x + y
  //再定义一个函数f2
  val f2 = (m: Int, n: Int) => m * n

  //main方法
  def main(args: Array[String]) {

    //调用m1方法，并传入f1函数
    val r1 = m1(f1)
    println(r1)

    //调用m1方法，并传入f2函数
    val r2 = m1(f2)
    println(r2)
  }

}
