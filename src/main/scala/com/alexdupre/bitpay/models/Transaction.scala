package com.alexdupre.bitpay.models

import java.time.OffsetDateTime

case class Transaction(amount: Long, confirmations: Int, time: Option[OffsetDateTime], receivedTime: OffsetDateTime, txid: String)
