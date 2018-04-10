package com.alexdupre.bitpay.models

import java.time.Instant

case class InvoiceNotification(id: String,
                               price: BigDecimal,
                               currency: String,
                               posData: Option[String],
                               url: String,
                               status: InvoiceState.Value,
                               amountPaid: Long,
                               paymentSubtotals: Map[String, Long],
                               paymentTotals: Map[String, Long],
                               invoiceTime: Instant,
                               expirationTime: Instant,
                               currentTime: Instant,
                               exceptionStatus: Option[InvoiceExceptionState.Value],
                               exchangeRates: Map[String, Map[String, BigDecimal]],
                               transactionCurrency: Option[String])
