name := "scala-sandbox"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-core" % "2.1.0"
libraryDependencies +=  "com.typesafe.slick" %% "slick" % "3.1.1"
libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1104-jdbc4"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.6"
libraryDependencies +=  "org.slf4j" % "slf4j-nop" % "1.6.4"