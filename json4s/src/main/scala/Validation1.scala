import org.joda.time.DateTime
import play.api.data.mapping.Rule
import play.api.data.mapping.json4s.{Rules => Json4SRules, Writes => Json4SWrites}
import org.json4s._
import org.json4s.jackson.JsonMethods._

/**
  * Created by felipe.almeida@vtex.com.br on 3/17/16.
  */
object Validation1 extends App {

  import Json4SRules._

  case class Person(name: String, age: Int)

  // for primitive types this is all i need to do
  implicit val personR = Rule.gen[JValue, Person]

  val json = parse("""{"name":"john", "age":34}""")

  println(personR.validate(json))

}
