import java.time.ZonedDateTime

import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser._
import io.circe.syntax._

case class Person(age: Int, name: String, dob: ZonedDateTime)

implicit val personDecoder:Decoder[Person] = deriveDecoder
implicit val personEncoder:Encoder[Person] = deriveEncoder


val p = Person(28, "felipe", ZonedDateTime.now())

val p2 = Person(23, "john", ZonedDateTime.now())

println(p.asJson)