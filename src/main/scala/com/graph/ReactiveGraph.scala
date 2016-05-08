package com.graph

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.softwaremill.react.kafka.KafkaMessages._
import com.softwaremill.react.kafka.{ConsumerProperties, ReactiveKafka}
import com.typesafe.config.{ConfigFactory, Config}
import com.typesafe.scalalogging.Logger
import org.apache.kafka.common.serialization.StringDeserializer
import org.reactivestreams.Publisher

/**
  * Created by mac_admin on 07/05/16.
  */
trait ReactiveGraph {

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer
  implicit val logger: Logger
  implicit val config: Config=ConfigFactory.load()

  private val kafkaConfig=config.getConfig("kafka")

  private val reactiveKafka=new ReactiveKafka()

  val consumerProps = ConsumerProperties(

    bootstrapServers = kafkaConfig.getString("kafka"),

    topic = kafkaConfig.getString("topicName"),

    groupId = kafkaConfig.getString("groupId"),

    valueDeserializer = new StringDeserializer()
  )

  //create publisher for 
  private val publisher: Publisher[StringConsumerRecord] =reactiveKafka.consume(consumerProps)


  def createKafkaGraph() = {


  }

}
