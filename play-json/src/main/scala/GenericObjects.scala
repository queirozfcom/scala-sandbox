import play.api.libs.json._

/**
  * Created by felipe.almeida@vtex.com.br on 30/03/16.
  */
object GenericObjects extends App {

  case class Person(name: String, data: JsValue)

  implicit val pr: Reads[Person] = Json.reads[Person]

  val str =
    """{
      "name":"John",
      "data": {
        "foo":"bar",
        "baz": {
          "quux": [
            "a","b","c"
          ]
        }
      }
      }"""

  val json = Json.parse(str)

  val res = json.validate[Person] match {
    case s: JsSuccess[Person] => s.get
    case e: JsError => Json.prettyPrint(JsError.toJson(e))
  }

  print(res)


}

