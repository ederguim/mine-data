package com.mine.data.v1.spark

import org.apache.spark.sql.{DataFrame, SaveMode}

object S3Read extends SparkSessionWrapper{

  def getBook(path: String): DataFrame = {
    spark.read.parquet(path)
  }
  def writeBook(dataFrame: DataFrame, path: String): Unit = {
    dataFrame
      .write
      .mode(SaveMode.Overwrite)
      .option("delimiter", ";")
      .option("header", "false")
      .parquet(path)
  }
}