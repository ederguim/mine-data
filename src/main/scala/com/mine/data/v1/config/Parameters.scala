package com.mine.data.v1.config

import com.mine.data.v1.model.ParametersModel
import scopt.OptionParser

object Parameters {

  def parse(args: Array[String]): ParametersModel = {

    new OptionParser[ParametersModel]("spark-mock-s3.jar") {
      head("S3 Mock")

      help('h', "help")

      opt[String]("hash-origem")
        .action((hashOrigem, params) => {
          params.copy(hashOrigem = cleanUpParameter(hashOrigem))
        })
        .text(optHelp(
          "[hash-origem] hash",
          "--hash-origem hash"
        ))
        .required()

      opt[String]("odin-pf-filter")
        .action((odinPfFilter, params) => {
          params.copy(odinPfFilter = cleanUpParameter(odinPfFilter))
        })
        .text(optHelp(
          "[odin-pf-filter] path odin pf filter",
          "--odin-pf-filter /path/pf.filter"
        ))
        .required()

      opt[String]("odin-pf-positive")
        .action((odinPfPositive, params) => {
          params.copy(odinPfPositive = cleanUpParameter(odinPfPositive))
        })
        .text(optHelp(
          "[odin-pf-positive] path odin pf positve",
          "--odin-pf-positive /path/pf.positive"
        ))
        .required()

      opt[String]("score")
        .action((score, params) => {
          params.copy(score = cleanUpParameter(score))
        })
        .text(optHelp(
          "[score] score",
          "--score score"
        ))
        .required()

      opt[Boolean]("flag-score")
        .action((flagScore, params) => {
          params.copy(flagScore = flagScore)
        })
        .text(optHelp(
          "[flag-score] true/false",
          "--flag-score true"
        ))
        .required()

      opt[Int]("start")
        .action((start, params) => {
          params.copy(start = start)
        })
        .text(optHelp(
          "[start] 0",
          "--start 0"
        ))
        .required()

      opt[Int]("end")
        .action((end, params) => {
          params.copy(end = end)
        })
        .text(optHelp(
          "[end] 10",
          "--end 10"
        ))
        .required()

      opt[String]("book-score")
        .action((bookScore, params) => {
          params.copy(bookScore = cleanUpParameter(bookScore))
        })
        .text(optHelp(
          "[book-score] path score",
          "--book-score /path/score"
        ))
        .required()

    }.parse(args, ParametersModel()) match {
      case Some(parameters) => parameters
      case _ => throw new IllegalArgumentException
    }
  }

  private def cleanUpParameter(param: String): String = param.replaceAll("\"", "").replaceAll("'", "")

  private def optHelp(description: String, usageExample: String): String = {
    s"""
			 | $description
			 |
			 | Example:
			 | $usageExample
			 |
			 |
			 |""".stripMargin
  }
}
