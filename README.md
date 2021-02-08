# bitpay-scala

[![Build Status](https://travis-ci.org/alexdupre/bitpay-scala.png?branch=master)](https://travis-ci.org/alexdupre/bitpay-scala)

An asynchronous / non-blocking Scala library for the BitPay API: https://bitpay.com/api

Supported resources:
- Currencies
- Invoices
- Ledgers
- Rates
- Tokens

## Artifacts

The latest release of the library is compiled with Scala 2.11, 2.12 and 2.13 and supports only Gigahorse with OkHttp backend as HTTP provider.

| Version | Artifact Id             | HTTP Provider   | Json Provider | Scala              |
| ------- | ----------------------- | --------------- | ------------- | ------------------ |
| 2.0     | bitpay                  | Gigahorse 0.5.x | Play-Json     | 2.11 & 2.12 & 2.13 |

If you're using SBT, add the following line to your build file:

```scala
libraryDependencies += "com.alexdupre" %% "bitpay" % "<version>"
```

## Usage

The [BitPay](https://github.com/alexdupre/bitpay-scala/blob/master/src/main/scala/com/alexdupre/bitpay/BitPay.scala) trait
contains all the public methods that can be called on the client object.

### Client Pairing

```scala
import com.alexdupre.bitpay._
import com.alexdupre.bitpay.models._

val identity = Identity.random()

val client: BitPay = BitPayClient(identity, testNet = true)

val token: Future[Token] = client.getPairingCode(label = "My Client", facade = "pos")

token foreach { t =>
  println(identity)
  pairingExpiration.foreach(println)
  pairingCode.foreach(println)
}

```

### Invoice Creation

```scala
import com.alexdupre.bitpay._
import com.alexdupre.bitpay.models._

val identity = Identity("...")

val client: BitPay = BitPayClient(identity, testNet = true)

val basicInvoice: Future[Invoice] = client.createInvoice(150, "USD")

val orderInfo  = OrderInfo(orderId = Some("A-123"), itemDesc = Some("An awesome item"), physical = Some(true))
val ipnParams  = IPNParams(notificationURL = Some("https://example.net/ipn"), transactionSpeed = Some(TransactionSpeed.Medium), fullNotifications = Some(true))
val buyer      = Some(BuyerInfo(email = "purchaser@example.net"))
val complexInvoice: Future[Invoice] = client.createInvoice(150, "USD", ipnParams, orderInfo, buyer)
```


### Instant Payment Notification

```scala
import com.alexdupre.bitpay.models.InvoiceNotification
import play.api.libs.json.Json

val ipn: InvoiceNotification = Json.parse("...").as[InvoiceNotification]
// notifications are not signed, so call BitPay.getInvoice(ipn.id) before processing the notification
```
