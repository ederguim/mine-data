package com.mine.data.v1.config

import com.mine.data.v1.model.ParametersModel
import scopt.OptionParser

object Parameters {

  def parse(args: Array[String]): ParametersModel = {

    new OptionParser[ParametersModel]("mine-data.jar") {
      head("Mine Data")

      help('h', "help")

      opt[String]("loto-facil")
        .action((lotoFacil, params) => {
          params.copy(lotoFacil = cleanUpParameter(lotoFacil))
        })
        .text(optHelp(
          "[loto-facil] src/main/resources/loto_facil.csv",
          "--loto-facil src/main/resources/loto_facil.csv"
        ))
        .optional()

      opt[String]("mega-sena")
        .action((megaSena, params) => {
          params.copy(megaSena = cleanUpParameter(megaSena))
        })
        .text(optHelp(
          "[mega-sena] src/main/resources/mega_sena.csv",
          "--mega-sena src/main/resources/mega_sena.csv"
        ))
        .optional()

      opt[Seq[String]]("aposta")
        .action((aposta, params) => {
          params.copy(aposta = aposta)
        })
        .text(optHelp(
          "[aposta] 1,2,3,4,5,6",
          "--aposta 1,2,3,4,5,6"
        ))
        .optional()

      opt[Seq[String]]("cols")
        .action((cols, params) => {
          params.copy(cols = cols)
        })
        .text(optHelp(
          "[cols] 1,2,3,4,5,6",
          "--cols 1,2,3,4,5,6"
        ))
        .optional()

      opt[Int]("start")
        .action((start, params) => {
          params.copy(start = start)
        })
        .text(optHelp(
          "[start] 1",
          "--start 1"
        ))
        .optional()

      opt[Int]("end")
        .action((end, params) => {
          params.copy(end = end)
        })
        .text(optHelp(
          "[end] 15",
          "--end 15"
        ))
        .optional()

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
