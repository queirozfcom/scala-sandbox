import org.scalatest.FlatSpec

/**
  * Created by felipe on 25/07/17.
  */
class SampleFlatSpec extends FlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size === 0)
  }
}
