package com.mine.data.v1.config

import com.mine.data.v1.model.ParametersModel
import scopt.OptionParser

object Parameters {

  def parse(args: Array[String]): ParametersModel = {

    new OptionParser[ParametersModel]("mine-data.jar") {
      head("Mine Data")

      help('h', "help")

      opt[String]("tipo")
        .action((tipo, params) => {
          params.copy(tipo = cleanUpParameter(tipo))
        })
        .text(optHelp(
          "[tipo] lotofacil",
          "--tipo lotofacil"
        ))
        .optional()

      opt[String]("origem")
        .action((origem, params) => {
          params.copy(origem = cleanUpParameter(origem))
        })
        .text(optHelp(
          "[origem] D:\\work\\repository\\data",
          "--origem D:\\work\\repository\\data"
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
