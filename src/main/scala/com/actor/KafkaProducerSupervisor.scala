package com.actor

import java.util.Properties

import akka.actor.{Props, Actor, ActorRef}
import akka.pattern._
import akka.routing.FromConfig
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.KafkaProducer

import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * factory object for KafkaProducerSupervisor Actor
  */
object KafkaProducerSupervisor{
  def props()(implicit timeout:Timeout)=Props(new KafkaProducerSupervisor())

}


/**
  * Created by Arun Sethia on 25/05/16.
  */
class KafkaProducerSupervisor(implicit timeout:Timeout) extends Actor{

  private var producer:Option[KafkaProducer[Integer, String]]=None
  private var kafkaProducerActor:Option[ActorRef]=None

  override def preStart()={

    val config=ConfigFactory.load()

    //these can be loaded from application.conf
    val kafkaProps=Map("bootstrap.servers" -> config.getString("kafka.servers"),
      "client.id" -> "producerstreamclient",
      "key.serializer" -> "org.apache.kafka.common.serialization.IntegerSerializer",
      "value.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
      "max.block.ms" -> "1000")

    val kafkaProperties=new Properties()

    kafkaProperties.putAll(kafkaProps)

    producer=Some(new KafkaProducer[Integer, String](kafkaProperties))

    //pool of actors
    kafkaProducerActor = Some(context.actorOf(FromConfig.
      props(KafkaProducerActor.props(producer.get,config.getString("kafka.topicName"))), "kafkaProducerRouter"))

  }


  def receive:Receive={
    case msg:String=>{
      val savedFlag=kafkaProducerActor.get.ask(msg).mapTo[Boolean]
      //send future to sender
      pipe(savedFlag) to sender()
    }
  }
}
