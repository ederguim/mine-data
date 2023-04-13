package com.mine.data.v1

import com.mine.data.v1.config.Parameters
import com.mine.data.v1.model.ParametersModel
import com.mine.data.v1.spark.DataRead.getData
import com.mine.data.v1.spark.SparkSessionWrapper
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, IntegerType}


object Application extends App with SparkSessionWrapper {
  val params: ParametersModel = Parameters.parse(args)

  private var df = getData(params)

  private val x = (1 to 15).toList
  x.foreach(row => {
    val index = row.toString
    val df1 = df.select(col(index).cast(IntegerType))
    df1
      .groupBy(index)
      .agg(
        count(index).as("quant"),
        format_string("%s%%", translate(format_number(round((count(index) / df1.count() * 100.0)
          .cast(DoubleType), 2), 2), ".", ","))
          .alias("percentual")
      ).orderBy(col("quant").desc)
      .show()
  })
}