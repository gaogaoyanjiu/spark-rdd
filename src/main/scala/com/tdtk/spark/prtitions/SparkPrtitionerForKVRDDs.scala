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
  *  - Optionally, a Partitioner for key-value RDDs (e.g. to say that the RDD is hash-partitioned)
  * 可选的：只有对于 key-value 的 RDD， 才会有 Partitioner，
  * 非 key-value的 RDD的 Partitioner的值是None，
  * Partitioner函数不但决定 了 RDD 本身的分片数量，
  * 也决定了 parents RDD Shuffle 输出时的分片数量
  *
  */
object SparkPrtitionerForKVRDDs {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("SparkPrtitionerForKVRDDs")
      .master("local[2]").getOrCreate()

    val rdd1 = spark.sparkContext.textFile("data/wc/")

    // 调用 spark 的 RDD的map方法，最终会调用scala原生的map方法
    val rdd2 = rdd1.map(x => (x, 1)) // 简写：val rdd2 = rdd1.map((_,1))

    rdd2.foreach(println)

    // 重新分区，会进行shuffle阶段，在网络之间进行传递，将数据打撒
    // 原来在a分区的一部分数据移动到b分区，b分区的一部分数据移动到a分区
    // 但是这样并不能说明 Partitioner 分区器的作用，那么就需要用到另外一个函数
    // val rdd3 = rdd2.repartition(2)

    // spark-shell 中需要导入下面的包
    import org.apache.spark.HashPartitioner

    /** **************************************************************************/
    // 会根据 key 对分区数量 进行（%）取模，决定数据放到哪个分区上，
    //  val rdd3 = rdd2.partitionBy(new HashPartitioner(2))
    /** **************************************************************************/
    // 当分区数量大于key的个数，那么取模的不到的就不会存放数据（是一个空文件）
    // val rdd3 = rdd2.partitionBy(new HashPartitioner(6))
    /** **************************************************************************/
    //计算出RDD一共有多少个Keys
    val partitionSize = rdd2.countByKey().toList.size
    println("计算key的个数 : " + partitionSize)
    val rdd3 = rdd2.partitionBy(new HashPartitioner(partitionSize))
    // 打印RDD 依赖关系 ,执行 partitionBy() 会经过shuffle 阶段
    println(rdd3.toDebugString)
    rdd3.saveAsTextFile("data/output/p-02")
    /** **************************************************************************/

    // 将统计结果写到一个分区文件中
    val rdd5 = rdd2.reduceByKey(_ + _, 1)
    // val rdd5 = rdd2.reduceByKey(_+_)
    // 打印RDD 依赖关系,执行 reduceByKey() 也会经过shuffle 阶段
    println(rdd5.toDebugString)
    rdd5.saveAsTextFile("data/output/p-03")
    spark.stop()
  }
}
