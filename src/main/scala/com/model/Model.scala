package com.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.model.Model.StringMessage
import spray.json.DefaultJsonProtocol

/**
  * This is model trait
  * Created by Arun Sethia
  */
object Model {
  case class StringMessage(msg: String)
}

trait MsgProtocol extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val stringMessageFormat=jsonFormat1(StringMessage.apply)
}
