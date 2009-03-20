import java.io._
import scala.io.Source
import scala.xml._
import scala.xml.dtd._
import org.apache.commons.io.FilenameUtils
import javax.xml.parsers.{DocumentBuilder,DocumentBuilderFactory}

class Slideshow(file: String) {
  // START immutability
  val basename = FilenameUtils.getBaseName(file)
  val path = FilenameUtils.getFullPath(file)
  // END immutability
  val slideText = Source.fromFile(file).getLines.toList.reduceLeft((a, b) => a + b)
  // START functions
  val slides = slideText.split("[\r\n]+---+[\r\n]+").map(
    (text) => new Slide(this, text))
  // END functions

  val doctype = DocType("html", 
                        PublicID("-//W3C//DTD XHTML 1.0 Strict//EN",
                                 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"), Nil)

  def filename(ext: String) = FilenameUtils.concat(path, basename + "." + ext)
  def title = (slides(0).toXML \ "h1").text

  def saveHTML() {
    scala.xml.XML.saveFull(filename("html"), toHTML, "UTF-8", true, doctype)
  }

  def savePDF() {
    saveHTML()
    (new PDFMaker(filename("html"))).createPDF(filename("pdf"))
  }
  
  override def toString = {
    toXML.toString
  }

  def toXML = {
    <slideshow>
      <title>{ title }</title>
      <slides>
        { slides.map((slide) => slide.toXML) }
      </slides>
    </slideshow>
  }

  def toHTML = {
    convertXMLtoHTML(toXML)
  }

  private def convertXMLtoHTML(xml: Node): Node = {
    xml match {
      case <slideshow>{ c @ _ * }</slideshow> =>
        <html>
          { c.map((child) => convertXMLtoHTML(child)) }
        </html>
      case <title>{ title }</title> =>
        <head>
          <title>{ title }</title>
          <meta name="defaultView" content="slideshow"/>
          <link rel="stylesheet" 
                href={ basename + ".css" }
                type="text/css" media="projection" id="slideProj"/>
          <link rel="stylesheet" 
                href="s6/outline.css" 
                type="text/css" media="screen" id="outlineStyle"/>
          <link rel="stylesheet" 
                href="s6/print.css" 
                type="text/css" media="print" id="slidePrint"/>
          <script src="s6/jquery.js" type="text/javascript"></script>
          <script src="s6/slides.js" type="text/javascript"></script>
        </head>
      case <slides>{ c @ _ * }</slides> =>
        <body>
          <div class="layout">
            <div id="controls"><!-- DO NOT EDIT --></div>
            <div id="currentSlide"><!-- DO NOT EDIT --></div>
            <div id="header"></div>
            <div id="footer">
              <h1></h1>
              <h2></h2>
            </div>
          </div>

          <div class="presentation">
            { c.map((child) => convertXMLtoHTML(child)) }
          </div>
        </body>
      case <slide>{ slide }</slide> => 
        <div class="slide">
          { slide }
        </div>
      case _ => xml
    }
  }
}
