import java.io._
import scala.io.Source
import org.xhtmlrenderer.pdf.ITextRenderer
import javax.xml.parsers.{DocumentBuilder,DocumentBuilderFactory}

object PDFMaker {
  val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
}

class PDFMaker(val fileName: String) {
  val file = new File(fileName)

  def text = Source.fromFile(file).getLines.toList.reduceLeft((a, b) => a + b)
  def baseURL = new File(fileName).toURI().toURL().toString()
  def document = PDFMaker.documentBuilder.parse(text)

  def createPDF(outputFileName: String) {
    val outputStream = new FileOutputStream(outputFileName)
    val renderer = new ITextRenderer
    renderer.setDocument(document, baseURL)
    renderer.layout
    renderer.createPDF(outputStream)
  }
}
