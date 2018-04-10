package com.alexdupre.bitpay.models

case class OrderInfo(orderId: Option[String] = None, itemDesc: Option[String] = None, physical: Option[Boolean] = None)
