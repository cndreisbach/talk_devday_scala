import org.scalatest._

class CSVLineTest extends FunSuite {
  test("A CSVLine should split up a string") {
    val strings = List("a", "b", "c")
    val csvline = new CSVLine(strings.reduceLeft((a, b) => a + "," + b))
    csvline.size == strings.size
  }
}
