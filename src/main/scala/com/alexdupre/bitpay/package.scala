package com.alexdupre

import java.time.{Instant, OffsetDateTime}
import java.util.UUID

import com.alexdupre.bitpay.models._
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.JsonAST.JValue
import org.json4s.ext._

import scala.language.implicitConversions

package object bitpay {

  implicit lazy val formats = DefaultFormats.lossless.withBigDecimal + JInstantSerializer + JOffsetDateTimeSerializer + UUIDSerializer +
    new EnumSerializer(InvoiceEvent) + new EnumSerializer(LedgerCode) +
    new EnumNameSerializer(InvoiceExceptionState) + new EnumNameSerializer(InvoiceState) +
    new EnumNameSerializer(PolicyMethod) + new EnumNameSerializer(PolicyType) +
    new EnumNameSerializer(RefundState) + new EnumNameSerializer(TransactionSpeed)

  implicit def toJson(id: UUID): JValue = Extraction.decompose(id)

  implicit def toJson(status: InvoiceState.Value): JValue = Extraction.decompose(status)

  implicit def toJson(dateTime: Instant): JValue = Extraction.decompose(dateTime)

  implicit def toJson(dateTime: OffsetDateTime): JValue = Extraction.decompose(dateTime)
}
