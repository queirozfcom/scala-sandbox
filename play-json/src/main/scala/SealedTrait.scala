import play.api.data.validation.ValidationError
import play.api.libs.json._
import play.api.libs.json.Reads._

/**
  * Created by felipe.almeida@vtex.com.br on 28/03/16.
  */
object SealedTrait {

  case class Person(name: String, age: Int, car: Car)

  sealed trait Car

  case object MercedesBenz extends Car

  case class NormalCar(model: String) extends Car

  def main(args: Array[String]) {


    implicit object CarReads extends Reads[Car] {
      def reads(json: JsValue): JsResult[Car] = json match {
        case JsString(s) => if (s == "mercedes") JsSuccess(MercedesBenz) else JsError(Seq(JsPath() -> Seq(ValidationError("error.invalid"))))
        case JsObject(obj) => obj.get("model") match {
          case Some(model) => model match {
            case JsString(str) => JsSuccess(NormalCar(str))
            case _ => JsError(Seq(JsPath() \ "model" -> Seq(ValidationError("error.expected.jsstring"))))
          }
          case None => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.car"))))
        }
        case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.invalid"))))
      }
    }

    implicit val pf: Reads[Person] = Json.reads[Person]

    val works =
      """{
      "name":"John",
      "age":20,
      "car":"mercedes"
      }"""

    val worksToo =
      """{
        "name":"John",
        "age":20,
        "car":{
          "model":"mondeo"
        }
        }"""

    val fails =
      """
        |{
        |  "name":"john",
        |  "age":20,
        |  "car": [ "an","array" ]
        |}
      """.stripMargin


    Seq(works, worksToo, fails).foreach { jsstr =>
      val json = Json.parse(jsstr)
      val res = json.validate[Person] match {
        case s: JsSuccess[Person] => s.get
        case e: JsError => Json.prettyPrint(JsError.toJson(e))
      }
      println(res)
    }

  }


}
