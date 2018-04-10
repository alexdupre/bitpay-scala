package com.alexdupre.bitpay

import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.SecureRandom

import org.spongycastle.asn1._
import org.spongycastle.asn1.sec.SECNamedCurves
import org.spongycastle.crypto.Digest
import org.spongycastle.crypto.digests.{RIPEMD160Digest, SHA256Digest}
import org.spongycastle.crypto.params.{ECDomainParameters, ECPrivateKeyParameters, ECPublicKeyParameters}
import org.spongycastle.crypto.signers.ECDSASigner
import org.spongycastle.math.ec.FixedPointCombMultiplier
import org.spongycastle.util.encoders.Hex

class Identity(secret: BigInteger) {

  import Identity._

  require(isValid(secret), "Identity is not valid")

  lazy val privKey = new ECPrivateKeyParameters(secret, params)
  lazy val pubKey  = new ECPublicKeyParameters(new FixedPointCombMultiplier().multiply(curve.getG, secret), params)

  lazy val privateIdentity = Hex.toHexString(secret.toByteArray.dropWhile(_ == 0))

  lazy val encodedPubKey  = pubKey.getQ.getEncoded(true)
  lazy val publicIdentity = Hex.toHexString(encodedPubKey)

  lazy val sin = {
    val pubKeyHash     = ripemd160(sha256(encodedPubKey))
    val version        = 0x0F.toByte
    val SINtype        = 0x02.toByte
    val preSINbyte     = version +: SINtype +: pubKeyHash
    val hash2Bytes     = sha256(sha256(preSINbyte))
    val first4Bytes    = hash2Bytes.take(4)
    val unencodedBytes = preSINbyte ++ first4Bytes
    base58(unencodedBytes)
  }

  def signRequest(url: String, payload: Array[Byte]): String = {
    def encode(rs: Array[BigInteger]) = {
      val baos = new ByteArrayOutputStream
      val der  = new DEROutputStream(baos)
      val v    = new ASN1EncodableVector
      rs.foreach(n => v.add(new ASN1Integer(n)))
      der.writeObject(new DERSequence(v))
      Hex.toHexString(baos.toByteArray())
    }

    val data  = url.getBytes("UTF-8") ++ Option(payload).getOrElse(Array.emptyByteArray)
    val hash  = sha256(data)
    val ecdsa = new ECDSASigner
    ecdsa.init(true, privKey)
    val rs = ecdsa.generateSignature(hash)
    encode(rs)
  }

  override def toString: String = s"""Identity("$privateIdentity")"""

}

object Identity {

  private[bitpay] lazy val prng = new SecureRandom

  private[bitpay] lazy val curve = SECNamedCurves.getByName("secp256k1")

  private[bitpay] lazy val params = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN())

  private[bitpay] def isValid(secret: BigInteger) = secret.compareTo(BigInteger.ZERO) > 0 && secret.compareTo(curve.getN) < 0

  def random(rnd: SecureRandom = prng): Identity = {
    val buf = new Array[Byte](32)
    def iterate(): BigInteger = {
      rnd.nextBytes(buf)
      val secret = new BigInteger(1, buf)
      if (isValid(secret)) secret else iterate()
    }
    new Identity(iterate())
  }

  def apply(hex: String): Identity = new Identity(new BigInteger(1, Hex.decode(hex)))

  private val base58alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"

  private[bitpay] def base58(input: Array[Byte]): String = {
    def encode(value: BigInt, accu: List[Char]): String = {
      if (value == 0) accu.mkString
      else {
        val (quotient, remainder) = value /% base58alphabet.length
        encode(quotient, base58alphabet(remainder.toInt) :: accu)
      }
    }

    val buffer  = encode(BigInt(1, input), Nil)
    val padding = input.takeWhile(_ == 0).map(_ => base58alphabet(0)).mkString
    padding + buffer
  }

  private[bitpay] def sha256(buffer: Array[Byte]): Array[Byte] =
    md(buffer, new SHA256Digest)

  private[bitpay] def ripemd160(buffer: Array[Byte]): Array[Byte] =
    md(buffer, new RIPEMD160Digest)

  private def md(buffer: Array[Byte], digest: Digest) = {
    digest.update(buffer, 0, buffer.length)
    val digestBytes = new Array[Byte](digest.getDigestSize())
    digest.doFinal(digestBytes, 0)
    digestBytes
  }

}
