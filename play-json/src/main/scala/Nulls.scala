import play.api.libs.json._

/**
  * Created by felipe.almeida@vtex.com.br on 28/03/16.
  */
object Nulls extends App {

  case class Person(name: String, age: Int, numChildren: Option[Int])

  implicit val pf: Reads[Person] = Json.reads[Person]

  val str =
    """{
      "name":"John",
      "age":20
      }"""

  val json = Json.parse(str)

  val res = json.validate[Person] match {
    case s: JsSuccess[Person] => s.get
    case e: JsError => Json.prettyPrint(JsError.toJson(e))
  }

  println(res)

}
