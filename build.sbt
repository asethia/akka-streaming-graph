name := "akka_streaming_graph"

version := "1.0"

scalaVersion := "2.11.8"

assemblyJarName in assembly := "akka_streaming_graph.jar"

libraryDependencies ++= Dependencies.basicDeps

assemblyMergeStrategy in assembly :={
  case PathList ("javax", "servlet", xs @ _ *) => MergeStrategy. first
  case PathList (ps @ _ *) if ps. last endsWith ".properties" => MergeStrategy. first
  case PathList (ps @ _ *) if ps. last endsWith ".xml" => MergeStrategy. first
  case PathList (ps @ _ *) if ps. last endsWith ".types" => MergeStrategy. first
  case PathList (ps @ _ *) if ps. last endsWith ".jnilib" => MergeStrategy. first
  case PathList (ps @ _ *) if ps. last endsWith ".dll" => MergeStrategy. first
  case PathList (ps @ _ *) if ps. last endsWith ".class" => MergeStrategy. first
  case "application.conf" => MergeStrategy. concat
  case "unwanted.txt" => MergeStrategy. discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly). value
    oldStrategy (x)
}

    