import scala.io.Source
import scala.xml.parsing.XhtmlParser
import com.petebevin.markdown.MarkdownProcessor

class Slide(text: String) {
  val mdProc = new MarkdownProcessor

  def toXML = {
    val parser = new XhtmlParser(Source.fromString(
      "<div class='slide'>" + mdProc.markdown(text) + "</div>"
    ))
    parser.nextch
    parser.document
  }
}
