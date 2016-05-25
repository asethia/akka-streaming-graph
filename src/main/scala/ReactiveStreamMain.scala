import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.graph.ReactiveGraph

/** This is for reactive stream main program
  * Created by Arun Sethia on 25/05/16.
  */
object ReactiveStreamMain extends App with ReactiveGraph{

  implicit val system = ActorSystem("ReactiveSystem")
  implicit val materializer = ActorMaterializer()
  implicit val timeout: Timeout = Timeout(4, TimeUnit.SECONDS)

  val reactiveGraph = createKafkaGraph.run()


}
