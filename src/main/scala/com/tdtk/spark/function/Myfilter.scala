package com.tdtk.spark.function

import org.apache.spark.{SparkConf, SparkContext}


/**
  * 测试 filter 函数 ： 过滤函数
  */
object Myfilter {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("sparkContext").setMaster("local[2]")
    val sc = new SparkContext(conf)

    println(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    // (1 to 9) 返回类型是：Range ,需要 将Range转换为list:(1 to 9).toList
    println((1 to 9))
    //  println((1 to 9).toList)

    val rddInt = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val rdd2 = sc.makeRDD((1 to 9).toList)


    rddInt.collect().mkString(",").foreach(print)
    println()
    println("=================================================================")

    //  rddInt.filter(_>5)
//    rddInt.filter(_ > 3).collect().mkString(",").foreach(print)
    rdd2.filter(_ > 3).collect().mkString(",").foreach(print)
    println()
    println("=================================================================")

  }

}
