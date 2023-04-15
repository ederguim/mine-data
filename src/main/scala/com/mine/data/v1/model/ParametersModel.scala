package com.mine.data.v1.model

case class ParametersModel(
                            megaSena: String = null,
                            lotoFacil: String = null,
                            aposta: Seq[String] = Seq(),
                            cols: Seq[String] = Seq(),
                            start: Int = 0,
                            end: Int = 0,
                          ) extends Serializable
