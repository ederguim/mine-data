package com.mine.data.v1

import com.mine.data.v1.config.Parameters
import com.mine.data.v1.model.Books.{bookPFFilter, bookPFPositive, bookScore}
import com.mine.data.v1.model.ParametersModel
import com.mine.data.v1.spark.S3Read.{getBook, writeBook}
import com.mine.data.v1.spark.SparkSessionWrapper

object Application extends App with SparkSessionWrapper {
  val params: ParametersModel = Parameters.parse(args)
  if (!params.flagScore) {
    val df = getBook(params.hashOrigem)
    val pfFilter = bookPFFilter(df)
    writeBook(pfFilter, params.odinPfFilter)
    val pfPositive = bookPFPositive(df)
    writeBook(pfPositive, params.odinPfPositive)
  } else if (params.flagScore) {
    val df = getBook(params.hashOrigem)
    val score = bookScore(df, params.score, params.start, params.end)
    writeBook(score, params.bookScore)
  }
}
