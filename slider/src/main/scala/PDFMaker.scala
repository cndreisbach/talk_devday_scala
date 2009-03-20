import java.io._
import scala.io.Source
import scala.collection.mutable.ArrayStack
import org.xhtmlrenderer.pdf.ITextRenderer
import com.lowagie.text._
import javax.xml.parsers.{DocumentBuilder,DocumentBuilderFactory}

object PDFMaker {
  val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
}

class PDFMaker(val fileName: String) {
  val file = new File(fileName)
  val fileSrc = Source.fromFile(file)
  val fontStack = new ArrayStack[String]
  
  def text = fileSrc.getLines.toList.reduceLeft((a, b) => a + b)
  def baseURL = file.toURI.toURL.toString
  def document(fileName: String) = PDFMaker.documentBuilder.parse(fileName)

  def addFont(fontFileName: String) {
    fontStack.push(fontFileName)
  }

  def createPDF(outputFileName: String) {
    createPDF(outputFileName, baseURL)
  }

  def createPDF(outputFileName: String, baseURL: String) {
    val outputStream = new FileOutputStream(outputFileName)
    val renderer = new ITextRenderer
    fontStack.foreach(font => renderer.getFontResolver.addFont(font, true))
    renderer.setDocument(document(fileName), baseURL)
    renderer.layout
    renderer.createPDF(outputStream)
  }
}
