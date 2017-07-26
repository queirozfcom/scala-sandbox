import com.queirozf.models.Person
import org.scalatest.FunSpec

/**
  * Created by felipe on 25/07/17.
  */
class SampleFunSpec extends FunSpec {

  describe("a person") {
    describe("when being created") {

      it("should throw an exception if it's given a zero or negative age") {
        assertThrows[IllegalArgumentException] {
          Person("john", "doe", -10)
        }
      }

      it("correctly formats first and last names") {
        assertResult("John Doe") {
          Person("john", "doe", 10).getFullName
        }
      }

    }
  }

}
