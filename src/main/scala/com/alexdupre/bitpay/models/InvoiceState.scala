package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait InvoiceState extends EnumEntry with Uncapitalised

object InvoiceState extends Enum[InvoiceState] with PlayJsonEnum[InvoiceState] {
  val values = findValues

  case object New       extends InvoiceState
  case object Paid      extends InvoiceState
  case object Confirmed extends InvoiceState
  case object Complete  extends InvoiceState
  case object Expired   extends InvoiceState
  case object Invalid   extends InvoiceState
}
