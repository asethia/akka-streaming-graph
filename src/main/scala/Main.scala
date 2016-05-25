import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.actor.KafkaProducerSupervisor
import com.graph.ReactiveGraph
import com.service.HttpServices

/**
  * This is main class to start HTTP Listener and Kafka Stream
  * Created by Arun Sethia
  */
object Main extends App  with HttpServices {

  implicit val system = ActorSystem("HTTPServiceSystem")
  implicit val materializer = ActorMaterializer()
  implicit val timeout: Timeout = Timeout(4, TimeUnit.SECONDS)


  kafkaProducerSuperVisor = Some(system.actorOf(KafkaProducerSupervisor.props,"supervisor"))

  //start HTTP Server
  Http().bindAndHandle(routes, "localhost", 8080)


}
