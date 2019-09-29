import scala.reflect.runtime.universe._

object TypedListsBad extends App {

  def processList(list: List[_]) = {
    list match {
      case l:List[String] =>  "it's a list of strings"
      case l:List[Int] =>  "it's a list of ints"
    }
  }

  val ints: List[Int] = List(1, 2, 3, 4, 5)
  val strings: List[String] = List("foo", "bar", "baz")

  println(processList(ints))

}
