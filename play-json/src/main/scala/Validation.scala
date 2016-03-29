import play.api.libs.json._

/**
  * Created by felipe.almeida@vtex.com.br on 3/9/16.
  */
object Validation extends App {

  case class Person(name: String, age: Int, numChildren: Int)

  implicit val pf:Reads[Person] = Json.reads[Person]

    val str =
      """{
      "name":"John",
      "age":20,
      "numChildren": 0
      }"""

    val json = Json.parse(str)

    val res = json.validate[Person] match {
      case s: JsSuccess[Person] => "ok"
      case e: JsError => Json.prettyPrint(JsError.toJson(e))
    }

    println(res)


}
