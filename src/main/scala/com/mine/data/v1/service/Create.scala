package com.mine.data.v1.service

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{current_timestamp, date_format, udf}

import scala.util.Random

object Create {

  def randomValue(start: Integer, end: Integer): Int = {
    val r = new scala.util.Random
    val r1 = start + r.nextInt(( end - start) + end)
    r1
  }

  def setRandomValue(validDF: DataFrame, columnName: String, start: Integer, end: Integer): DataFrame = {
    val udfValueInt = udf(() => randomValue(start, end))
    validDF.withColumn(columnName, udfValueInt())
  }

  def setDataRef(validDF: DataFrame, dt_ref: String, dt_t0: String): DataFrame = {
    var dfSeq = validDF
    dfSeq = dfSeq.withColumn(dt_ref, date_format(current_timestamp(),"yyyy-MM-dd").as("yyyy-MM-dd"))
    dfSeq = dfSeq.withColumn(dt_t0 , date_format(current_timestamp(),"yyyy-MM-dd HH:mm:ss").as("yyyy-MM-dd HH:mm:ss"))
    dfSeq
  }

  def validateValue(): Int = {
    val r = new scala.util.Random
    val r1 = 0 + r.nextInt((1 - 0) + 1)
    r1
  }

  def setValue(validDF: DataFrame, columnName: String): DataFrame = {
    val udfValueInt = udf(() => validateValue())
    validDF.withColumn(columnName, udfValueInt())
  }

  def validateValueStr(): String = {
    val alpha = "ABMNS"
    val randStr = (1 to 1).map(_ => alpha(Random.nextInt(alpha.length))).mkString
    randStr
  }

  def setValueStr(validDF: DataFrame, columnName: String): DataFrame = {
    val udfValueStr = udf(() => validateValueStr())
    validDF.withColumn(columnName, udfValueStr())
  }

}
