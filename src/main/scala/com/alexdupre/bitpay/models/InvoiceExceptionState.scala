package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._
import play.api.libs.json.{JsBoolean, JsResult, JsSuccess, JsValue, Json, Reads, Writes}

sealed trait InvoiceExceptionState extends EnumEntry with Uncapitalised

object InvoiceExceptionState extends Enum[InvoiceExceptionState] with PlayJsonEnum[InvoiceExceptionState] {
  val values = findValues

  case object PaidPartial extends InvoiceExceptionState
  case object PaidOver    extends InvoiceExceptionState
  case object PaidLate    extends InvoiceExceptionState

  implicit val readEither: Reads[Either[Boolean, InvoiceExceptionState]] = new Reads[Either[Boolean, InvoiceExceptionState]] {
    override def reads(json: JsValue): JsResult[Either[Boolean, InvoiceExceptionState]] = json match {
      case JsBoolean(b) => JsSuccess(Left(b))
      case _            => json.validate[InvoiceExceptionState].map(s => Right(s))
    }
  }
  implicit val writeEither: Writes[Either[Boolean, InvoiceExceptionState]] = new Writes[Either[Boolean, InvoiceExceptionState]] {
    override def writes(o: Either[Boolean, InvoiceExceptionState]): JsValue = o.fold(b => JsBoolean(b), s => Json.toJson(s))
  }

}
