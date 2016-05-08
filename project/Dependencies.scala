import sbt._

import Versions._

object Dependencies {

  val akkaStream = "com.typesafe.akka" %% "akka-stream-experimental" % akkaStreamV

  val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaV

  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaV

  val akkaReactiveKafka= "com.softwaremill.reactivekafka" %% "reactive-kafka-core" % reactKafkaV

  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingV

  val basicDeps = Seq(scalaLogging,akkaStream,
                      akkaActor,akkaSlf4j,akkaReactiveKafka,akkaHttpSprayJson)

}