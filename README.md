# spark-rdd
spark RDD算子 练习


RDD（弹性分布式数据集）
算子宏观分类：
  1、Transformation（变换/转换）-----------懒加载的（这种变换并不触发提交作业，完成作业中间过程处理）
  2、Action        （动作/行动）-----------立即执行（这类算子会触发 SparkContext 提交 Job 作业,并将数据输出 Spark系统）    

Spark的常见算子的分类：
（一）Transformation算子:这种变换并不触发提交作业
    1）Value数据类型：针对处理的数据项是Value型的数据
    2）Key-Value数据类型：针对处理的数据项是Key-Value型的数据对
（二）Action算子：这类算子会触发SparkContext提交Job作业。

1）Value数据类型的Transformation算子　　
　　一、输入分区与输出分区一对一型
　　　　1、map算子
　　　　2、flatMap算子
　　　　3、mapPartitions算子
　　　　4、glom算子
　　二、输入分区与输出分区多对一型　
　　　　5、union算子
　　　　6、cartesian算子
　　三、输入分区与输出分区多对多型
　　　　7、grouBy算子
　　四、输出分区为输入分区子集型
　　　　8、filter算子
　　　　9、distinct算子
　　　　10、subtract算子
　　　　11、sample算子
     　 12、takeSample算子
 　　五、Cache型
　　　　13、cache算子　　
　　　　14、persist算子

2）Key-Value数据类型的Transfromation算子
　　一、输入分区与输出分区一对一
　　　　15、mapValues算子
　　二、对单个RDD或两个RDD聚集
　　　单个RDD聚集
　　　　16、combineByKey算子
　　　　17、reduceByKey算子
　　　　18、partitionBy算子
 　　两个RDD聚集
　　　　19、Cogroup算子
　　三、连接
　　　　20、join算子
　　　　21、leftOutJoin和 rightOutJoin算子

3）Action算子
　　一、无输出
　　　　22、foreach算子
　　二、HDFS
　　　　23、saveAsTextFile算子
　　　　24、saveAsObjectFile算子
　　三、Scala集合和数据类型
　　　　25、collect算子
　　　　26、collectAsMap算子
 　　　 27、reduceByKeyLocally算子
 　　　 28、lookup算子
　　　　29、count算子
　　　　30、top算子
　　　　31、reduce算子
　　　　32、fold算子
　　　　33、aggregate算子