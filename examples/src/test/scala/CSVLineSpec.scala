import org.specs._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._

object CSVLineSpec extends Specification {
  "A CSVLine" should {
    "split up a string with commas" in {
      check(
        (strings: List[String]) => {
          val csvline = new CSVLine(strings.reduceLeft((a, b) => a + "," + b))
          csvline.size == strings.size
        }      
      )
    }
  }
}
