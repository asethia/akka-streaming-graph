import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import com.graph.ReactiveGraph
import com.service.HttpServices
import com.softwaremill.react.kafka.{ReactiveKafka, ProducerMessage, ConsumerProperties}
import com.softwaremill.react.kafka.KafkaMessages.StringConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.reactivestreams.Publisher

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
