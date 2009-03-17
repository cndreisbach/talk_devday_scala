import org.scalatest.prop.PropSuite
import org.scalacheck._

class CSVLineTest extends PropSuite {
  test("A CSVLine should split up a string") {
    check(
      (strings: List[String]) => {
        val csvline = new CSVLine(strings.reduceLeft((a, b) => a + "," + b))
        csvline.size == strings.size
      }
    )
  }
}
