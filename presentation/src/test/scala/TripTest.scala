import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

// START dsltest
class TripTest extends FunSuite with ShouldMatchers {
  test("should be able to take a trip") {
    val myTrip = new Trip from "Durham, NC" to "Nowhere, WV" to "Detroit, MI" to "Nunavut"
    myTrip.stops should equal (4)
  }
}
// END dsltest