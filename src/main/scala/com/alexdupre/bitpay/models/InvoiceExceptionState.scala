package com.alexdupre.bitpay.models

object InvoiceExceptionState extends Enumeration {

  val PaidPartial = Value("paidPartial")
  val PaidOver    = Value("paidOver")
  val PaidLate    = Value("paidLate")
}
