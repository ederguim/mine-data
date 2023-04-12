package com.mine.data.v1

import com.mine.data.v1.config.Parameters
import com.mine.data.v1.model.ParametersModel
import com.mine.data.v1.spark.DataRead.getData
import com.mine.data.v1.spark.SparkSessionWrapper
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.col

object Application extends App with SparkSessionWrapper {
  val params: ParametersModel = Parameters.parse(args)
  private var df = getData(params)

  private val filter: Column =
    col("01").equalTo("02")
      .or(col("02").contains("03"))
      .or(col("03").contains("04"))
      .or(col("04").contains("05"))
      .or(col("05").contains("09"))
      .or(col("06").contains("10"))
      .or(col("07").contains("11"))
      .or(col("08").contains("12"))
      .or(col("09").contains("13"))
      .or(col("10").contains("14"))
      .or(col("11").contains("19"))
      .or(col("12").contains("21"))
      .or(col("13").contains("22"))
      .or(col("14").contains("23"))
      .or(col("15").contains("25"))

  private var filtered = df.filter(filter)

  filtered.show()

  val df1 = df.groupBy(col("01")).count()
  val df2 = df.groupBy(col("02")).count()
  val df3 = df.groupBy(col("03")).count()
  val df4 = df.groupBy(col("04")).count()
  val df5 = df.groupBy(col("05")).count()
  val df6 = df.groupBy(col("06")).count()
  val df7 = df.groupBy(col("07")).count()
  val df8 = df.groupBy(col("08")).count()
  val df9 = df.groupBy(col("09")).count()
  val df10 = df.groupBy(col("10")).count()
  val df11 = df.groupBy(col("11")).count()
  val df12 = df.groupBy(col("12")).count()
  val df13 = df.groupBy(col("13")).count()
  val df14 = df.groupBy(col("14")).count()
  val df15 = df.groupBy(col("15")).count()

  df1.show()
  df2.show()
  df3.show()
  df4.show()
  df5.show()
  df6.show()
  df7.show()
  df8.show()
  df9.show()
  df10.show()
  df11.show()
  df12.show()
  df13.show()
  df14.show()
  df15.show()

}