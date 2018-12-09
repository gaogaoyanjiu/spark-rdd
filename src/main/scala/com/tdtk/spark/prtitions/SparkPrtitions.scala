package com.tdtk.spark.prtitions

import org.apache.spark.sql.SparkSession

/**
  *  - A list of partitions
  *  - A function for computing each split
  *  - A list of dependencies on other RDDs
  *  - Optionally, a Partitioner for key-value RDDs (e.g. to say that the RDD is hash-partitioned)
  *  - Optionally, a list of preferred locations to compute each split on (e.g. block locations for
  * an HDFS file)
  *
  * ***********************************************************
  *
  *  - A list of partitions ：一组分片的测试，根据最后一个RDD从后往前RDD推断，形成stage阶段
  * partitions数据量小 放在内存，放不下会溢出到磁盘
  *
  * - A function for computing each split ：一个函数会作用到每个分区上，Spark 中 RDD 的计算是以分片为单位的，
  * 每个 RDD 都会实现 compute 函数以达到这个目的，compute 函数会对迭代器进行复合，不需要保存每次计算的结果
  *
  */
object SparkPrtitions {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("SparkPrtitions")
      .master("local[2]").getOrCreate()

    //  val rdd1 = spark.sparkContext.textFile("data/wc/")
    // 手动指定分区个数
    val rdd1 = spark.sparkContext.textFile("data/wc/", 4)
    // 查看输入的分片个数
    println("输入切片个数 : " + rdd1.partitions.length)

    // 调用 spark 的 RDD的map方法，最终会调用scala原生的map方法
    val rdd2 = rdd1.map(x => (x, 1)) // 简写：val rdd2 = rdd1.map((_,1))

    rdd2.foreach(println)
    rdd2.saveAsTextFile("data/output/p-01")

    // 打印RDD 依赖关系
    println(rdd2.toDebugString)

    spark.stop()
  }
}
