import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.Serialization.write

/**
  * Created by felipe.almeida@vtex.com.br on 07/06/16.
  */
object Nulls extends App {

  implicit val formats = DefaultFormats

  case class Person(name:String, maybeAge: Option[Int])

  println(write(Person("john",None)))
  //  prints {"name":"john"}

  val noAge: Option[Int] = None
  val someAge:Option[Int] = Some(45)

  val withNone = ("foo"->"foo") ~ ("age" -> noAge)
  val withSome = ("foo"->"foo") ~ ("age" -> someAge)

  println(write(withNone)) // prints {"foo":"foo"}
  println(write(withSome)) // prints {"foo":"foo","age":45}

}
