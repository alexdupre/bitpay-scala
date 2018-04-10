# bitpay-scala

[![Build Status](https://travis-ci.org/alexdupre/bitpay-scala.png?branch=master)](https://travis-ci.org/alexdupre/bitpay-scala)

An asynchronous / non-blocking Scala library for the BitPay API: https://bitpay.com/api

Supported resources:
- Currencies
- Invoices
- Ledgers
- Rates
- Tokens

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

val token: Future[Invoice] = client.createInvoice(150, "USD")
```


### Instant Payment Notification

```scala
import com.alexdupre.bitpay.formats
import com.alexdupre.bitpay.models._
import org.json4s.native.Serialization

val ipn: InvoiceNotification = Serialization.read[InvoiceNotification]("...")
// notifications are not signed, so call BitPay.getInvoice(ipn.id) before processing the notification
```