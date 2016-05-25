package com.actor

import akka.actor.{ActorLogging, Actor}

/**
  * This is kafka consumer actor to consume messages and store into kafka topic
  * Created by Arun Sethia on 24/05/16.
  */
class KafkaConsumerActor extends Actor with ActorLogging{

  def receive:Receive={
    case msg:String => {
      //store message into Kafka
    }
    case _=>  log.error(s"This is unknown message")
  }

}
