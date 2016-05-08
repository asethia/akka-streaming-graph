# akka-streaming-graph
This is program creates kafka stream from given topic and print them on console. <br/>

The program also create HTTP listener port 8080 to demonstrate message insert into a TOPIC, so kafka stream can read same. The REST service endpoint will be http://localhost:8080/msg/create to post a string message; which will be sent to given topic.

