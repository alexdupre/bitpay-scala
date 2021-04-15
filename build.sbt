name := "bitpay"

organization := "com.alexdupre"

version := "2.1"

crossScalaVersions := Seq("2.11.12", "2.12.13", "2.13.5")

scalaVersion := "2.13.5"

scalacOptions := List("-feature", "-unchecked", "-deprecation", "-explaintypes", "-encoding", "UTF8", "-language:postfixOps")

resolvers += Resolver.typesafeRepo("releases")

def playJsonVersion(scalaVersion: String) =
  CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, scalaMajor)) if scalaMajor >= 12 => "2.9.2"
    case Some((2, scalaMajor)) if scalaMajor >= 11 => "2.7.4"
    case _ =>
      throw new IllegalArgumentException(s"Unsupported Scala version $scalaVersion")
  }

def playJsonExtensionsVersion(scalaVersion: String) =
  CrossVersion.partialVersion(scalaVersion) match {
    //case Some((2, scalaMajor)) if scalaMajor >= 12 => "0.42.0"
    case Some((2, scalaMajor)) if scalaMajor >= 11 => "0.40.2"
    case _ =>
      throw new IllegalArgumentException(s"Unsupported Scala version $scalaVersion")
  }

lazy val enumeratumVersion = "1.6.1"

libraryDependencies ++= List(
  "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0",
  "com.typesafe.play" %% "play-json" % playJsonVersion(scalaVersion.value),
  "ai.x" %% "play-json-extensions" % playJsonExtensionsVersion(scalaVersion.value),
  "com.beachape" %% "enumeratum" % enumeratumVersion,
  "com.beachape" %% "enumeratum-play-json" % enumeratumVersion,
  "org.bouncycastle" % "bcprov-jdk15to18" % "1.68",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "test"
)

publishTo := sonatypePublishToBundle.value

publishMavenStyle := true

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

sonatypeProjectHosting := Some(xerial.sbt.Sonatype.GitHubHosting("alexdupre", "bitpay-scala", "Alex Dupre", "ale@FreeBSD.org"))

buildInfoKeys := Seq[BuildInfoKey](version)

buildInfoPackage := s"${organization.value}.${name.value}"

enablePlugins(BuildInfoPlugin)
