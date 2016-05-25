package com.actor



import java.util.concurrent.{TimeUnit, Future}

import akka.actor.{Props, ActorLogging, Actor}
import org.apache.kafka.clients.producer.{RecordMetadata, ProducerRecord, KafkaProducer}

/**
  * factor class
  * Created by Arun Sethia on 24/05/16.
  */

object KafkaProducerActor{
  //passing one producer to all pool of actors
  def props(producer:KafkaProducer[Integer, String],topicName:String)=Props(classOf[KafkaProducerActor],producer,topicName)
}

/**
  * This is kafka consumer actor to consume messages and store into kafka topic
  * Created by Arun Sethia on 24/05/16.
  */
class KafkaProducerActor(producer:KafkaProducer[Integer, String],topicName:String) extends Actor with ActorLogging{

  override def preStart()={
        //do any initialization require
  }

  def receive:Receive={
    case msg:String => {
      log.info(s"Message $msg received to save into Kafka topic $topicName ")
      val record = new ProducerRecord[Integer, String](topicName, msg)
      val status: Future[RecordMetadata] = producer.send(record)
      val rec: RecordMetadata = status.get(2, TimeUnit.SECONDS)
      if (rec.offset() > 0) {
        log.info(s"message sent and store into kafka $topicName topic")
        sender() ! true
      } else {
        log.info(s"message failed to store into kafka $topicName topic")
        sender() ! false
      }
    }
    case _=>  {
      log.error(s"This is unknown message")
    }
  }

}
