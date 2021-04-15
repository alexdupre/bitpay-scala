package com.alexdupre.bitpay.models

import play.api.libs.json.{Json, Reads}

case class DataResponse[T](data: T)

object DataResponse {
  implicit def read[T: Reads] = Json.reads[DataResponse[T]]
}
