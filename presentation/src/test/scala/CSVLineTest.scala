import org.scalatest._
import org.scalatest.matchers._

class CSVLineTest extends FunSuite with ShouldMatchers {
  test("A CSVLine should split up a string") {
    val strings = List("a", "b", "c")
    val csvline = new CSVLine(strings.reduceLeft((a, b) => a + "," + b))
    csvline.size should equal (strings.size)
  }
}
