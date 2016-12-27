name := "flood-kinesis"

version := "1.0"

scalaVersion := "2.11.8"

val slf4jVersion = "1.7.16"
val log4jVersion = "1.2.17"

libraryDependencies += "com.amazonaws" % "aws-java-sdk-kinesis" % "1.10.77"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.0"
libraryDependencies += "org.slf4j" % "slf4j-api" % slf4jVersion
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % slf4jVersion
libraryDependencies += "log4j" % "log4j" % log4jVersion
