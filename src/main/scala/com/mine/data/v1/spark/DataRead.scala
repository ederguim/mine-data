package com.mine.data.v1.spark

import org.apache.spark.sql.{DataFrame, SaveMode}

object DataRead extends SparkSessionWrapper {

  def getData(path: String): DataFrame = {
    spark.read
      .option("header", "true")
      .option("delimiter", ";")
      .csv(path)
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