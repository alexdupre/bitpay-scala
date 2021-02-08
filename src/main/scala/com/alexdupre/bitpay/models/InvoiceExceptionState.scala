package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait InvoiceExceptionState extends EnumEntry with Uncapitalised

object InvoiceExceptionState extends Enum[InvoiceExceptionState] with PlayJsonEnum[InvoiceExceptionState] {
  val values = findValues

  case object PaidPartial extends InvoiceExceptionState
  case object PaidOver    extends InvoiceExceptionState
  case object PaidLate    extends InvoiceExceptionState
}
