import play.api.libs.json._

/**
  * Created by felipe.almeida@vtex.com.br on 30/03/16.
  */
object PrimitiveTypes extends App {

  case class Person(name: String, data: Map[String, String])

  implicit val pr: Reads[Person] = Json.reads[Person]

  // doesn't work
  // implicit val mr:Reads[Map[String,Any]] = Json.reads[Map[String,Any]]

  val str =
    """{
      "name":"John",
      "data":20
      }"""

  val json = Json.parse(str)

  val res = json.validate[Person] match {
    case s: JsSuccess[Person] => s.get
    case e: JsError => Json.prettyPrint(JsError.toJson(e))
  }

  print(res)

}

