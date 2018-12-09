package com.tdtk.spark.prtitions

import org.apache.spark.sql.SparkSession


/**
  *  SparkSession:
  *  实质上是SQLContext和HiveContext的组合（未来可能还会加上StreamingContext），
  *  所以在SQLContext和HiveContext上可用的API在SparkSession上同样是可以使用的。
  *
  *
  */
object sparkSession {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder
                .master("local[2]")
                .appName("sparkSession") .getOrCreate()

//    var rdd1 = spark.sparkContext.parallelize((1 to 9).toList)
//    var rdd1 = spark.sparkContext.parallelize((1 to 9).toArray[Int])
//    var rdd1 = spark.sparkContext.parallelize(Array(1,2,3,4,5,6,7,8,9))
    var rdd1 = spark.sparkContext.parallelize(List(1,2,3,4,5,6,7,8,9))

    rdd1.collect().mkString(",").foreach(print)
    println()
    println("=================================================================")

    rdd1.filter(_ > 3).collect().mkString(",").foreach(print)
    println()
    println("=================================================================")
  }

}
