package com.mine.data.v1.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {

  val config = new SparkConf()
    .setAppName("S3 Mock")
    .setMaster("local[*]")

  lazy val spark: SparkSession =
  SparkSession
    .builder
    .config(config)
    .getOrCreate()

  spark.sparkContext.hadoopConfiguration.set("fs.s3a.endpoint", "s3.sa-east-1.amazonaws.com")
  spark.sparkContext.hadoopConfiguration.set("spark.hadoop.fs.s3.useRequesterPaysHeader", "true")
  spark.sparkContext.hadoopConfiguration.set("spark.hadoop.fs.s3a.useRequesterPaysHeader", "true")
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
  spark.sparkContext.hadoopConfiguration.set("fs.s3.impl", "org.apache.hadoop.fs.s3.S3AFileSystem")
}
