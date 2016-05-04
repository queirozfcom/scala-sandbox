import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read,write}

/**
  * Created by felipe on 04/05/16.
  */
object BasicReadWrite extends App{

  implicit val formats = DefaultFormats

  case class Person(name:String,age:Int)

  val john = Person("john",45)

  println(write(john))

  val maryAsString = """{"name":"mary", "age":89} """

  println(read[Person](maryAsString))

  val invalidPerson = """{"name":"david","numPets":2}"""

  println(read[Person](invalidPerson))


}
