name := "bitpay"

organization := "com.alexdupre"

version := "2.4"

crossScalaVersions := Seq("2.12.16", "2.13.8")

scalaVersion := "2.13.8"

scalacOptions := List("-feature", "-unchecked", "-deprecation", "-explaintypes", "-encoding", "UTF8", "-language:postfixOps")

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= List(
  "com.eed3si9n"      %% "gigahorse-okhttp"     % "0.7.0",
  "com.typesafe.play" %% "play-json"            % "2.9.3",
  "ai.x"              %% "play-json-extensions" % "0.40.2",
  "com.beachape"      %% "enumeratum"           % "1.7.0",
  "com.beachape"      %% "enumeratum-play-json" % "1.7.0",
  "org.bouncycastle"   % "bcprov-jdk15to18"     % "1.71",
  "org.slf4j"          % "slf4j-api"            % "2.0.0",
  "ch.qos.logback"     % "logback-classic"      % "1.3.0" % "test"
)

publishTo := sonatypePublishToBundle.value

publishMavenStyle := true

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

sonatypeProjectHosting := Some(xerial.sbt.Sonatype.GitHubHosting("alexdupre", "bitpay-scala", "Alex Dupre", "ale@FreeBSD.org"))

buildInfoKeys := Seq[BuildInfoKey](version)

buildInfoPackage := s"${organization.value}.${name.value}"

enablePlugins(BuildInfoPlugin)
