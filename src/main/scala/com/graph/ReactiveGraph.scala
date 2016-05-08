package com.graph

import akka.actor.ActorSystem
import akka.stream.scaladsl._
import akka.stream.{ActorMaterializer, ClosedShape}
import com.model.Model.StringMessage
import com.softwaremill.react.kafka.KafkaMessages._
import com.softwaremill.react.kafka.{ConsumerProperties, ReactiveKafka}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.common.serialization.StringDeserializer
import org.reactivestreams.Publisher

import scala.concurrent.Future

/**
  * Create Reactive Kafka Stream
  *
  * Created by Arun Sethia
  */
trait ReactiveGraph {

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer
  implicit val config: Config = ConfigFactory.load()

  //kafka configuration read from application.conf
  private val kafkaConfig = config.getConfig("kafka")

  private val reactiveKafka = new ReactiveKafka()

  val consumerProps = ConsumerProperties(

    bootstrapServers = kafkaConfig.getString("servers"),

    topic = kafkaConfig.getString("topicName"),

    groupId = kafkaConfig.getString("groupId"),

    valueDeserializer = new StringDeserializer()
  )

  //create publisher for
  private val publisher: Publisher[StringConsumerRecord] = reactiveKafka.consume(consumerProps)


  /**
    * create Kafka Graph
    *
    * @return
    */
  def createKafkaGraph() = {
    import GraphDSL.Implicits._
    RunnableGraph.fromGraph(GraphDSL.create() { implicit builder =>

      //async parallel message

      val flow = builder.add(Flow[StringConsumerRecord].mapAsync[StringMessage](parallelism = 10)(msg =>
        processValidateMessage(msg.value())))

      //this can be sink to store information into database
      val sink = builder.add(Sink.foreach(println))

      Source.fromPublisher(publisher) ~> flow ~> sink

      ClosedShape
    })
  }

  private def processValidateMessage(msg: String) = {
    Future {
      //perform complex validation or processing
      StringMessage(msg)
    }
  }

}
