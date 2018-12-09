package com.tdtk.spark.prtitions

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 创建SparkContext一般要经过下面几个步骤：
  * a). 导入Spark的类和隐式转换
  * import org.apache.spark.{SparkContext, SparkConf}
  * import org.apache.spark.SparkContext._
  *
  * b). 构建Spark应用程序的应用信息对象SparkConf
  * val conf = new SparkConf().setAppName(appName).setMaster(master_url)
  *
  * c). 利用SparkConf对象来初始化SparkContext
  * val sc = new SparkContext(conf)
  *
  * d). 创建RDD、执行相应的Transformation和Action并得到最终结果。
  *
  * e). 关闭Context
  */
object sparkContext {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("sparkContext").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val rdd1= sc.parallelize(Array(1 to 9), 2)

    rdd1.foreach(println)

    sc.stop()
  }

}
