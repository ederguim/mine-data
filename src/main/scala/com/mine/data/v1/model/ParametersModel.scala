package com.mine.data.v1.model

import org.apache.commons.lang3.StringUtils.EMPTY

case class ParametersModel(
                            tipo: String = EMPTY,
                            origem: String = EMPTY
                          ) extends Serializable
