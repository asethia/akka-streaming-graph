import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.graph.ReactiveGraph
import com.service.HttpServices

/**
  * This is main class to start HTTP Listener and Kafka Stream
  * Created by Arun Sethia
  */
object Main extends App with ReactiveGraph with HttpServices{

  implicit  val system=ActorSystem("ReactiveSystem")
  implicit val materializer=ActorMaterializer()

  val reactiveGraph=createKafkaGraph.run()


  //start HTTP Server
  Http().bindAndHandle(routes, "localhost", 8080)

}
