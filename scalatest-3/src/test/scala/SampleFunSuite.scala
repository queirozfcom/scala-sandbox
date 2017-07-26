import org.scalatest.FunSuite

/**
  * Created by felipe on 25/07/17.
  */
class SampleFunSuite extends FunSuite {

  val lst = List.empty[String]
  val arr = Array("foo", "bar")

  test("test list methods") {
    assert(lst.size == 0)
  }

  test("test array methods") {

    assert(arr(0) == "foo")

    assertThrows[ArrayIndexOutOfBoundsException] {
      arr(13)
    }

  }

}
