import java.text.SimpleDateFormat

import org.joda.time.DateTime
import java.text.ParseException
import play.api.data.mapping.{Failure => ValidationFailure, Success => ValidationSuccess, From, Rule}
import play.api.data.mapping.json4s.{Rules => Json4SRules, Writes => Json4SWrites}
import org.json4s._
import org.json4s.jackson.JsonMethods._
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._

import scala.util.{Failure, Success, Try}

/**
  * Created by felipe.almeida@vtex.com.br on 3/17/16.
  */
object Validation2 extends App {

  import Json4SRules._

  case class Person(name: String, age: Int, lastLogin: DateTime)

  implicit val dateTimeR: Rule[JValue, DateTime] = Rule.fromMapping {
    case JString(s) => {

      Try(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(s)) match {
        case Success(jd) => ValidationSuccess(new DateTime(jd))
        case Failure(e) => e match {
          case pe: ParseException => ValidationFailure(Seq(ValidationError("error.badformat")))
          case thr => throw thr
        }
      }
    }
    case _ => ValidationFailure(Seq(ValidationError("error.notajstring")))
  }

  // for primitive types this is all i need to do
  implicit val personR: Rule[JValue, Person] =

    From[JValue] { js =>
      ((js \ "name").read[String] ~
        (js \ "age").read[Int] ~
        (js \ "lastLogin").read[DateTime]
        ) (Person.apply _)
    }

  println(personR.validate(parse("""{"name":"john", "age":34}""")))
  println(personR.validate(parse("""{"name":"john", "age":34, "lastLogin": 321}""")))
  println(personR.validate(parse("""{"name":"john", "age":34, "lastLogin":"2016-22-13T4:50:31Z"}""")))
  println(personR.validate(parse("""{"name":"john", "age":34, "lastLogin":"2016-22-13"}""")))
  println(personR.validate(parse("""{"name":"john", "age":34, "lastLogin":"2016-03-13T04:50:31-03:00"}""")))

}
