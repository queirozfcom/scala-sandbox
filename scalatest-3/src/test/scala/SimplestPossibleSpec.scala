import org.scalatest.FlatSpec

/**
  * Created by felipe on 25/07/17.
  */
class SimplestPossibleSpec extends FlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }
}
