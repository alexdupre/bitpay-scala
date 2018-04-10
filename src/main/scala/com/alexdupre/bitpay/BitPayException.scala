package com.alexdupre.bitpay

abstract class BitPayException(message: String) extends Exception(message)

case class BitPayProtocolException(error: String = "BitPay Protocol Error") extends BitPayException(error)

case class BitPayReturnedException(error: String) extends BitPayException(error)

case class BitPayLogicException(error: String) extends BitPayException(error)
