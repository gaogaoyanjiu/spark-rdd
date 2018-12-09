package com.tdtk.spark.function

import org.apache.spark.sql.SparkSession


/**
  * 测试map函数：输入函数,返回新的RDD,输入的是一个参数，返回的是1到多个参数
  *
  * #WordCount, 第二个效率低
  * sc.textFile("/root/words.txt").flatMap(x=>x.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).collect
  * sc.textFile("/root/words.txt").flatMap(x=>x.split(" ")).map((_,1)).groupByKey.map(t=>(t._1, t._2.sum)).collect
  */
object Mymap {
  def main(args: Array[String]): Unit = {


    val spark = SparkSession.builder().appName("Mymap")
      .master("local[2]").getOrCreate()

    // 方式一：reduceByKey(_+_)
    val rdd1 = spark.sparkContext.textFile("data/words.txt")
    rdd1.flatMap(_.split(" ")).map((_,1)).foreach(println)
    rdd1.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2,false).foreach(println)

    // 方式二：.groupByKey.map(t=>(t._1, t._2.sum))
//    val rdd2 = spark.sparkContext.textFile("data/words.txt")
//    rdd2.flatMap(_.split(" ")).map((_,1)).groupByKey.map(t=>(t._1, t._2.sum)).foreach(println)


    //    val df = spark.read.format("json").textFile("data/words.txt")
    //    df.printSchema()
    //    df.show(true)


    spark.stop()
  }

}
