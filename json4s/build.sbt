name := "json4s"

version := "1.0"

scalaVersion := "2.10.6"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.2",
  "org.json4s" %% "json4s-jackson" % "3.3.0",
  "org.json4s" %% "json4s-ext" % "3.3.0",
  "io.github.jto" %% "validation-json" % "1.1",
  "io.github.jto" %% "validation-json4s" % "1.1"

)
    