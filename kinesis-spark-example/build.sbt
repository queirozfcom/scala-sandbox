name := "kinesis-spark-example"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "joda-time" % "joda-time" % "2.8.2"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.5.2" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kinesis-asl" % "1.5.2"
libraryDependencies += "com.amazonaws" % "amazon-kinesis-client" % "1.6.1"
libraryDependencies += "com.amazonaws" % "amazon-kinesis-producer" % "0.10.2"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"