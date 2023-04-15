package com.mine.data.v1

import com.mine.data.v1.config.Parameters
import com.mine.data.v1.model.ParametersModel
import com.mine.data.v1.service.Analytics
import com.mine.data.v1.spark.DataRead.getData
import com.mine.data.v1.spark.SparkSessionWrapper
import org.apache.spark.internal.Logging


object Application extends App with SparkSessionWrapper with Logging {
  val params: ParametersModel = Parameters.parse(args)
  private val options: Option[ParametersModel] = Some(params)
    options match {
      case Some(value) if (value.megaSena != null) =>
        val df = getData(params.megaSena)
        val ganhadores = Analytics.verificaGanhadoresV2(df, params.cols, params.aposta)
        ganhadores.show(numRows = 10, truncate = false)
        val percentual = Analytics.percentual(df, params.start, params.end)
        percentual.foreach(row =>  row.show())
      case Some(value) if (value.lotoFacil != null) =>
        val df = getData(params.lotoFacil)
        val ganhadores = Analytics.verificaGanhadoresV2(df, params.cols, params.aposta)
        ganhadores.show(numRows = 10, truncate = false)
        val percentual = Analytics.percentual(df, params.start, params.end)
        percentual.foreach(row => row.show())
      case Some(_) => logError("Parametro invalido")
      case None => throw new Exception("Parametro não fornecido")
    }
}