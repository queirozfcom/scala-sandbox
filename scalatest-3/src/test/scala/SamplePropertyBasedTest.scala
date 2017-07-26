import com.queirozf.models.Person
import org.scalatest.FunSuite
import org.scalatest.prop.PropertyChecks

/**
  * Created by felipe on 25/07/17.
  */
class SamplePropertyBasedTest extends FunSuite with PropertyChecks {

  test("first and last names") {
    forAll { (a: String, b: String) =>
      assert {
        Person(a, b, 10).getFullName.toLowerCase.startsWith(a.toLowerCase) &&
        Person(a, b, 10).getFullName.toLowerCase.endsWith(b.toLowerCase)
      }
    }
  }
}
