import collection.mutable.Stack
import org.scalatest._

import scala.collection.mutable

class SampleFlatSpec extends FlatSpec with Matchers {

  // Stack has been deprecated in 2.12, but this is just for demonstration purposes

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be(2)
    stack.pop() should be(1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new mutable.Stack[Int]
    a[NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }
}
