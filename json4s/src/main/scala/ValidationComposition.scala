import org.joda.time.DateTime
import java.text.ParseException

import play.api.data.mapping.{Failure => ValidationFailure, Success => ValidationSuccess, _}
import play.api.data.mapping.json4s.{Rules => Json4SRules, Writes => Json4SWrites}
import org.json4s._
import org.json4s.jackson.JsonMethods._
import play.api.data.validation.ValidationError

/**
  * Created by felipe.almeida@vtex.com.br on 26/04/16.
  */
object ValidationComposition extends App {

  // i want to add a validation rule specifying that the addresses must be an array of size 1

  import Json4SRules._

  case class Person(name: String, addresses: List[String])

  val personR: Rule[JValue, Person] = Rule.gen[JValue, Person]

  val fullRule = new Rule[JValue, Person] {
    override def validate(jvalue: JValue): VA[Person] = personR.validate(jvalue) match {
      case ValidationSuccess(person) => {
        person.addresses.size match {
          case 1 => ValidationSuccess(person)
          case _ => ValidationFailure(Seq(Path \ "addresses" -> Seq(ValidationError("constraint.generic"))))
        }
      }
      case ValidationFailure(errors) => ValidationFailure(errors)
    }
  }


  println(fullRule.validate(parse("""{"name":"john","addresses": ["foo"]} """)))
  println(fullRule.validate(parse("""{"name":"john","addresses": ["foo","bar"]} """)))
  println(fullRule.validate(parse("""{"name":"john","addresses": ""} """)))


}
