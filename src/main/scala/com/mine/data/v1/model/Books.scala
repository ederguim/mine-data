package com.mine.data.v1.model

import com.mine.data.v1.service.Create.{setDataRef, setRandomValue, setValue, setValueStr}
import org.apache.spark.sql.DataFrame

object Books {

  def bookPFFilter(dataframe: DataFrame): DataFrame = {
    var dfSeq = dataframe
    dfSeq = setDataRef(dfSeq, "dt_ref", "dt_t0")
    dfSeq = setValue(dfSeq, "flag_menor_idade")
    dfSeq = setValue(dfSeq, "flag_idade_missing")
    dfSeq = setValue(dfSeq, "flag_inexistente")
    dfSeq = setValue(dfSeq, "flag_bloqueio")
    dfSeq = setValue(dfSeq, "flag_vip")
    dfSeq = setValue(dfSeq, "flag_obito")
    dfSeq = setValue(dfSeq, "flag_sit_reg_rec")
    dfSeq = setValue(dfSeq, "flag_sit_pend_reg_rec")
    dfSeq = setValue(dfSeq, "flag_sit_nula_rec")
    dfSeq = setValue(dfSeq, "flag_sit_susp_rec")
    dfSeq = setValue(dfSeq, "flag_sit_canc_rec")
    dfSeq = setValue(dfSeq, "flag_sit_obito_rec")
    dfSeq = setValue(dfSeq, "flag_bloqueio_endereco")
    dfSeq = setValue(dfSeq, "bucket")
    dfSeq
  }

  def bookPFPositive(dataFrame: DataFrame): DataFrame = {
    var dfSeq = dataFrame
    dfSeq = setValueStr(dfSeq, "autoriza_pos_b2b")
    dfSeq = setDataRef(dfSeq, "dt_ref", "dt_t0")
    dfSeq
  }

  def bookScore(dataFrame: DataFrame, score: String, start: Integer, end: Integer): DataFrame = {
    var dfSeq = dataFrame
    dfSeq = setDataRef(dfSeq, "dt_ref", "dt_t0")
    dfSeq = setRandomValue(dfSeq, score, start, end)
    dfSeq
  }

}
