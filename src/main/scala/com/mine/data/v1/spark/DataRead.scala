package com.mine.data.v1.spark

import com.mine.data.v1.model.ParametersModel
import org.apache.spark.sql.{DataFrame, SaveMode}

object DataRead extends SparkSessionWrapper {

  def getData(params: ParametersModel): DataFrame = {
    if (params.tipo.equalsIgnoreCase("lotofacil"))
      spark.read
        .option("header", "true")
        .option("delimiter", ";")
        .csv(params.origem)
    else
      null
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