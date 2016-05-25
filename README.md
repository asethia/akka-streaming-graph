# akka-streaming-graph

This sample code povides following functionalities:
 
1. Akka HTTP REST Service
2. Store incoming REST service request to Kafka topic
3. Akka Stream Graph - Kafka Producer to stream messages


This is program creates kafka stream from given topic and print them on the console.  The program also create HTTP listener port 8080 to demonstrate message insert into a TOPIC, so kafka stream can read the same. 

The REST service endpoint will be http://localhost:8080/msg/create to post a string message; which will be sent to the given topic. The REST Service store messages into "test_demo_topic"; which is being read by kafka stream graph and print on console.



