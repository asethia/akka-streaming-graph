package com.service

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.server.{Directives}
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.model.Model.StringMessage
import com.model.MsgProtocol
import akka.pattern._

import scala.concurrent.Future

/**
  * This is Http Service
  * Created by Arun Sethia
  */
trait HttpServices extends Directives with MsgProtocol{
  implicit val system: ActorSystem
  implicit val timeout: Timeout
  implicit val materializer: ActorMaterializer
  var kafkaProducerSuperVisor:Option[ActorRef]=None

  /**
    * service route
    * @return
    */
  def routes={
    pathPrefix("msg"){
      path("create"){ //msg/create
        post{
          entity(as[StringMessage]){ msgObj =>
              onComplete(saveIncomingMessage(msgObj.msg)){ returnFlag =>
                complete(returnFlag.toString)
              }
          }
        }
      }
    }
  }

  /**
    * save message to kafka
    * @param msg
    * @return
    */
  private def saveIncomingMessage(msg:String):Future[Boolean]={
    kafkaProducerSuperVisor.get.ask(msg).mapTo[Boolean]
  }




}
