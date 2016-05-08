package com.service

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.http.scaladsl.server.{Directives}
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.model.Model.StringMessage
import com.model.MsgProtocol

/**
  * This is Http Service
  * Created by Arun Sethia
  */
trait HttpServices extends Directives with MsgProtocol{
  implicit val system: ActorSystem
  implicit val timeout: Timeout = Timeout(4, TimeUnit.SECONDS)
  implicit val materializer: ActorMaterializer

  def routes={
    pathPrefix("msg"){
      path("create"){ //msg/create
        post{
          entity(as[StringMessage]){ msgObj =>
              complete("Ok")
          }
        }
      }
    }
  }

}
