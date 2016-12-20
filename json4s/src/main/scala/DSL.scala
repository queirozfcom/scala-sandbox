import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.Serialization.write
/**
  * Created by felipe.almeida@vtex.com.br on 07/06/16.
  */
object DSL extends App {
  implicit val formats = DefaultFormats

  // connect tuples with "~" to create a json object
  val obj1: JObject = ("foo", "bar") ~ ("baz", "quux")
  println(write(obj1))
  // {"foo":"bar","baz":"quux"}

  // you can also use "->" notation to create tuples
  val obj2: JObject = ("foo" -> "bar") ~ ("baz" -> "quux")
  println(write(obj2))
  // {"foo":"bar","baz":"quux"}

  // use any sequence to make an array of json elements
  val array1: JArray = Seq(obj1,obj2)
  println(write(array1))
  // [{"foo":"bar","baz":"quux"},{"foo":"bar","baz":"quux"}]

  val array2: JArray = Seq("foo","bar")
  println(write(array2))
  // ["foo","bar"]
}
