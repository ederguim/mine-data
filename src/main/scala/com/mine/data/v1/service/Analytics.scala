package com.mine.data.v1.service

import breeze.numerics.constants.c
import com.mine.data.v1.model.Constants.{concurso, data, num_acerto, perc, quant, quant_acerto, str_format}
import com.mine.data.v1.spark.SparkSessionWrapper
import org.apache.spark.internal.Logging
import org.apache.spark.sql.{Column, DataFrame, Dataset, Row}
import org.apache.spark.sql.functions.{array, _}
import org.apache.spark.sql.types.{DoubleType, IntegerType}

object Analytics extends SparkSessionWrapper with Logging {

  def percentual(df: DataFrame, strat: Int, end: Int): List[Dataset[Row]] = {
    val iterable = (strat to end).toList
    iterable.map(row => {
      val index = row.toString
      val df1 = df.select(col(index).cast(IntegerType))
      df1
        .groupBy(index)
        .agg(
          count(index).as(quant),
          format_string(str_format, translate(format_number(round((count(index) / df1.count() * 100.0)
            .cast(DoubleType), 2), 2), ".", ","))
            .alias(perc)
        ).orderBy(col(quant).desc)
    })
  }

  def verificaGanhadores(df: DataFrame, aposta: Seq[String]): Dataset[Row] = {
    df.select(
      col(concurso),
      col(data),
      array(col("1"), col("2"), col("3"), col("4"), col("5"), col("6"), col("7"), col("8"), col("9"), col("10"), col("11"), col("12"), col("13"), col("14"), col("15")).as("num_sorteado"),
      size(array_intersect(array(col("1"), col("2"), col("3"), col("4"), col("5"), col("6"), col("7"), col("8"), col("9"), col("10"), col("11"), col("12"), col("13"), col("14"), col("15")),
        array(aposta.map(lit(_)): _*)))
        .as(quant_acerto),
      array_intersect(array(col("1"), col("2"), col("3"), col("4"), col("5"), col("6"), col("7"), col("8"), col("9"), col("10"), col("11"), col("12"), col("13"), col("14"), col("15")),
        array(aposta.map(lit(_)): _*)).
        as(num_acerto)
    )
      .orderBy(col(quant_acerto).desc)
  }

  def verificaGanhadoresV2(df: DataFrame, cols: Seq[String], aposta: Seq[String]): Dataset[Row] = {
    df.select(
      col(concurso),
      col(data),
      array(cols.map(lit(_)): _*)
        .as(num_acerto),
      size(array_intersect(array(cols.map(lit(_)): _*),
        array(aposta.map(lit(_)): _*)))
        .as(quant_acerto),
      array_intersect(array(cols.map(lit(_)): _*),
        array(aposta.map(lit(_)): _*)).
        as(num_acerto)
    )
      .orderBy(col(quant_acerto).desc)
  }
}
