name := "bitpay"

organization := "com.alexdupre"

version := "1.3"

crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1")

scalaVersion := "2.13.1"

scalacOptions := List("-feature", "-unchecked", "-deprecation", "-explaintypes", "-encoding", "UTF8", "-language:postfixOps")

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= List(
  "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0",
  "org.json4s" %% "json4s-native" % "3.6.7",
  "org.json4s" %% "json4s-ext" % "3.6.7",
  "com.madgag.spongycastle" % "core" % "1.58.0.0",
  "org.slf4j" % "slf4j-api" % "1.7.28",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "test"
)

publishTo := sonatypePublishToBundle.value

publishMavenStyle := true

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

sonatypeProjectHosting := Some(xerial.sbt.Sonatype.GitHubHosting("alexdupre", "bitpay-scala", "Alex Dupre", "ale@FreeBSD.org"))

buildInfoKeys := Seq[BuildInfoKey](version)

buildInfoPackage := s"${organization.value}.${name.value}"

enablePlugins(BuildInfoPlugin)
