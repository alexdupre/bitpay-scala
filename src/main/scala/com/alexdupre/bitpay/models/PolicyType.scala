package com.alexdupre.bitpay.models

object PolicyType extends Enumeration {

  val SIN     = Value("sin")
  val Access  = Value("access")
  val Events  = Value("events")
  val ID      = Value("id")
  val Session = Value("session")
}
