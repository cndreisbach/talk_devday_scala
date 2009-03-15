import java.io._
import scala.io.Source
import org.xhtmlrenderer.pdf.ITextRenderer

class PDFMaker(val html: String) {
  def this(htmlFile: File) = {
    this(Source.fromFile(htmlFile).getLines.toList.reduceLeft((a, b) => a + b))
  }
}