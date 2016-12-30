

name := "oms-data-feed"

version := "1.0"

scalaVersion := "2.11.8"



scalaVersion := "2.11.8"

val sparkVersion = "2.0.0"
val amazonawsVersion = "1.11.31"
val akkaV = "2.4.9"

resolvers += Resolver.mavenLocal

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kinesis-asl" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"


libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % akkaV
libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % akkaV

libraryDependencies += "com.amazonaws" % "aws-java-sdk-kinesis" % amazonawsVersion
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.0"

libraryDependencies += "org.everit.json" % "org.everit.json.schema" % "1.4.0"
libraryDependencies += "com.github.coveo" % "uap-java" % "1.3.1-coveo1"
libraryDependencies += "com.amazonaws" % "aws-java-sdk-s3" % amazonawsVersion
libraryDependencies += "com.github.cb372" %% "scalacache-guava" % "0.9.1"
//libraryDependencies += "com.amazonaws" % "aws-java-sdk-cloudwatch" % amazonawsVersion
//libraryDependencies += "com.amazonaws" % "aws-java-sdk-core" % amazonawsVersion


assemblyMergeStrategy in assembly := {
  case PathList("com", "typesafe", xs@_*) => MergeStrategy.first
  case PathList("org", "aopalliance", xs@_*) => MergeStrategy.last
  case PathList("javax", "inject", xs@_*) => MergeStrategy.last
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
  case PathList("javax", "activation", xs@_*) => MergeStrategy.last
  case PathList("org", "apache", xs@_*) => MergeStrategy.last
  case PathList("com", "google", xs@_*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.last
  case PathList("com", "codahale", xs@_*) => MergeStrategy.last
  case PathList("com", "yammer", xs@_*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}