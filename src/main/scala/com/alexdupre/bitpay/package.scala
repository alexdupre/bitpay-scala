package com.alexdupre

import java.time.{Instant, OffsetDateTime}
import java.util.UUID

import com.alexdupre.bitpay.models._
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.JsonAST.JValue
import org.json4s.ext._

import scala.language.implicitConversions

package object bitpay {

  private val defaultSerializers = DefaultFormats.lossless.withBigDecimal

  private val timeSerializers = Seq(JOffsetDateTimeSerializer, JInstantSerializer)

  private val javaSerializers = Seq(UUIDSerializer)

  private val enumSerializers = Seq(InvoiceEvent, LedgerCode).map(e => new EnumSerializer(e))

  private val enumNameSerializers =
    Seq(InvoiceExceptionState, InvoiceState, PolicyMethod, PolicyType, RefundState, TransactionSpeed).map(e => new EnumNameSerializer(e))

  implicit lazy val formats = defaultSerializers ++ timeSerializers ++ javaSerializers ++ enumSerializers ++ enumNameSerializers

  implicit def toJson(id: UUID): JValue = Extraction.decompose(id)

  implicit def toJson(status: InvoiceState.Value): JValue = Extraction.decompose(status)

  implicit def toJson(dateTime: Instant): JValue = Extraction.decompose(dateTime)

  implicit def toJson(dateTime: OffsetDateTime): JValue = Extraction.decompose(dateTime)
}
