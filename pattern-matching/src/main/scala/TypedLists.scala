object TypedLists extends App {
  val list1:List[Int] = List(1,2,3,4,5,6)

  // and then try to pattern match on it
  list1 match {
    case list: List[Int] => "It's a list of ints"
    case _ => "List of unknown type"
  }

}
