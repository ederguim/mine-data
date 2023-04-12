package com.mine.data.v1.model

import org.apache.commons.lang3.StringUtils.EMPTY

case class ParametersModel(
                            hashOrigem: String = EMPTY,
                            odinPfFilter: String = EMPTY,
                            odinPfPositive: String = EMPTY,
                            score: String = EMPTY,
                            flagScore: Boolean = false,
                            bookScore: String = EMPTY,
                            start: Integer = 0,
                            end: Integer = 0,
                          ) extends Serializable
