import org.scalatest._

import com.queirozf.sandbox.{VTTest => App}

class VTTestSpec extends FlatSpec with Matchers {

  "test1" should "return as in problem description" in {

    val lst = List(1, 2, 3, 4, 6)
    val target = 6

    val expected = List(List(1, 2, 3), List(2, 4), List(6))

    App.getCombinations(lst, target) shouldEqual expected

  }

  "test2" should "return as in problem description" in {

    val source = 1
    val target = 4

    val paths = Map(1 -> List(2, 3), 2 -> List(4), 3 -> List(5))

    App.findPath(source, target, paths) shouldEqual Some(List(1, 2, 4))

  }

  "test2" should "return shortest path when multiple paths are available" in {

    val source = 1
    val target = 4

    val paths = Map(1 -> List(2, 3), 2 -> List(3, 4), 3 -> List(4))

    App.findPath(source, target, paths) shouldEqual Some(List(1, 2, 4))

  }

  "test2" should "return empty when no paths could be found" in {

    val source = 1
    val target = 5

    val paths = Map(1 -> List(2), 2 -> List(4), 3 -> List(5))

    App.findPath(source, target, paths) shouldEqual Some(List.empty[Int])

  }

  "test2" should "return none when not both number are valid nodes" in {

    val source = 82
    val target = 41

    val paths = Map(1 -> List(2, 3), 2 -> List(4), 3 -> List(5))

    App.findPath(source, target, paths) shouldEqual None

  }

}