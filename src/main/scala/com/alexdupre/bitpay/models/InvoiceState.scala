package com.alexdupre.bitpay.models

object InvoiceState extends Enumeration {

  val New       = Value("new")
  val Paid      = Value("paid")
  val Confirmed = Value("confirmed")
  val Complete  = Value("complete")
  val Expired   = Value("expired")
  val Invalid   = Value("invalid")
}
