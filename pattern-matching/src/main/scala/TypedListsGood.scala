import scala.reflect.runtime.universe._

object TypedListsGood extends App {

  def processList[T: TypeTag](list: List[T]) = {
    typeOf[T] match {
      case t if t =:= typeOf[String] => "it's a list of strings"
      case t if t =:= typeOf[Int]    => "it's a list of ints"
    }
  }

  val ints: List[Int] = List(1, 2, 3, 4, 5)
  val strings: List[String] = List("foo", "bar", "baz")

  println(processList(ints))

}
