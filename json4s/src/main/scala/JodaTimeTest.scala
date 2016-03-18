import java.text.SimpleDateFormat

import org.json4s._
import org.json4s.ext.JodaTimeSerializers
import org.json4s.jackson.JsonMethods._

import org.joda.time._

/**
  * Created by felipe.almeida@vtex.com.br on 3/17/16.
  */
object JodaTimeTest extends App {

  implicit val formats = new DefaultFormats {
    override def dateFormatter = {
      new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    }
  } ++ JodaTimeSerializers.all

  case class Person(name: String, birth: DateTime)

  val stroffset = """{"name":"john","birth":"2016-03-13T04:50:31-03:00"}"""
  val strz = """{"name":"john","birth":"2016-03-13T04:50:31Z"}"""
  val strnotz = """{"name":"john","birth":"2016-03-13T04:50:31"}"""

  println(parse(stroffset).extractOpt[Person])
  println(parse(strz).extractOpt[Person])
  println(parse(strnotz).extractOpt[Person])
  println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2016-03-13T04:50:31-03:00"))
  println(new DateTime("2016-03-13T04:50:31-03:00"))

}
