import org.json4s._
import org.json4s.jackson.JsonMethods._
import play.api.data.mapping.{Failure => ValidationFailure, Rule, Success => ValidationSuccess}

// intellij informs that this is not needed but it IS!
import play.api.data.mapping.json4s.Rules._

/**
  * Created by felipe.almeida@vtex.com.br on 31/03/16.
  */
object ValidationSeq extends App {

  case class BillingQueryModel(
                                params: Map[String, String],
                                datePeriod: String,
                                aggregation: Option[String],
                                distinct: Option[String])

  object BillingQueryModel {
    def validate = Rule.gen[JValue, BillingQueryModel].validate _
  }


  val works =
    """{ "params":{
      |   "foo":"bar"
      | },
      | "datePeriod":"foobar",
      | "aggregation":"foo"
      | }""".stripMargin

  val fails =
    """
      |{
      | "params":"foobar",
      | "datePeriod":"21"
      |}
    """.stripMargin

  val alsoFails =
    """
      |{
      | "params":{
      | "foobar":123
      | },
      | "datePeriod":"21"
      |}
    """.stripMargin

  Seq(works, fails, alsoFails).foreach { js =>
    BillingQueryModel.validate(parse(js)) match {
      case ValidationSuccess(model) => println(model)
      case ValidationFailure(e) => println(e)
    }
  }

}
