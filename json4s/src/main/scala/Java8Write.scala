import java.text.SimpleDateFormat

import org.json4s._
import org.json4s.ext.JavaTypesSerializers
import org.json4s.jackson.JsonMethods._

import java.time.ZonedDateTime
import java.time.format._

import scala.util.{Failure, Success, Try}

/**
  * Created by felipe.almeida@vtex.com.br on 3/17/16.
  */
object Java8Write extends App {

  case object ZDTSerializer extends CustomSerializer[ZonedDateTime](format => ( {
    case JString(s) => ZonedDateTime.parse(s)
  }, {
    case zdt: ZonedDateTime => JString(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")))
  }))


  implicit val formats = DefaultFormats + ZDTSerializer

  case class Person(name: String, birth: ZonedDateTime)

  val stroffset = """{"name":"john","birth":"2016-03-13T04:50:31-03:00"}"""
  val strz = """{"name":"john","birth":"2016-03-13T04:50:31Z"}"""
  val strnotz = """{"name":"john","birth":"2016-03-13T04:50:31"}"""

  println(parse(stroffset).extractOpt[Person])
  println(parse(strz).extractOpt[Person])
  println(parse(strnotz).extractOpt[Person])
  println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2016-03-13T04:50:31-03:00"))
  println(ZonedDateTime.now())

}
