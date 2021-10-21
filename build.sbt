name := "bitpay"

organization := "com.alexdupre"

version := "2.3"

crossScalaVersions := Seq("2.12.15", "2.13.6")

scalaVersion := "2.13.6"

scalacOptions := List("-feature", "-unchecked", "-deprecation", "-explaintypes", "-encoding", "UTF8", "-language:postfixOps")

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= List(
  "com.eed3si9n"      %% "gigahorse-okhttp"     % "0.5.0",
  "com.typesafe.play" %% "play-json"            % "2.9.2",
  "ai.x"              %% "play-json-extensions" % "0.40.2",
  "com.beachape"      %% "enumeratum"           % "1.7.0",
  "com.beachape"      %% "enumeratum-play-json" % "1.7.0",
  "org.bouncycastle"   % "bcprov-jdk15to18"     % "1.69",
  "org.slf4j"          % "slf4j-api"            % "1.7.32",
  "ch.qos.logback"     % "logback-classic"      % "1.2.6" % "test"
)

publishTo := sonatypePublishToBundle.value

publishMavenStyle := true

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

sonatypeProjectHosting := Some(xerial.sbt.Sonatype.GitHubHosting("alexdupre", "bitpay-scala", "Alex Dupre", "ale@FreeBSD.org"))

buildInfoKeys := Seq[BuildInfoKey](version)

buildInfoPackage := s"${organization.value}.${name.value}"

enablePlugins(BuildInfoPlugin)
