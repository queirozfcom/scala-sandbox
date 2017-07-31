import java.time.ZonedDateTime

import io.circe._
import io.circe.generic.auto._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.JsonCodec
/**
  * Created by felipe on 02/07/17.
  */
object CustomEncoders extends App{

  implicit val encodeZdt: Encoder[ZonedDateTime] = Encoder.encodeString.contramap[ZonedDateTime]( zdt => zdt.toString)

  case class Person(age: Int, name: String, dob: ZonedDateTime)


  val p = Person(28, "felipe", ZonedDateTime.now())

  val p2 = Person(23, "john", ZonedDateTime.now())

  println(p.asJson)

}
