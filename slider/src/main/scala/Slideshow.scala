import java.io._
import scala.io.Source
import scala.xml.XML
import scala.xml.dtd._
import org.apache.commons.io.FilenameUtils

class Slideshow(filename: String) {
  val basename = FilenameUtils.getBaseName(filename)
  val path = FilenameUtils.getFullPath(filename)
  val slideText = Source.fromFile(filename).getLines.toList.reduceLeft((a, b) => a + b)
  val slides = slideText.split("[\r\n]+---+[\r\n]+").map((text) => new Slide(this, text))

  val doctype = DocType("html", 
                        PublicID("-//W3C//DTD XHTML 1.0 Strict//EN",
                                 "http://www.w3.org/TR/xhtml1/DTD/strict.dtd"), Nil)

  def title = (slides(0).toXML \ "h1").text

  def saveFile() {
    saveFile(FilenameUtils.concat(path, basename + ".html"))
  }
  
  def saveFile(filename: String) {
    scala.xml.XML.saveFull(filename, toXML, "UTF-8", true, doctype)
  }
  
  override def toString = {
    val writer = new StringWriter
    scala.xml.XML.write(writer, toXML, "UTF-8", true, doctype)
    writer.toString
  }

  // TODO move extra css and JS out of here
  def toXML = {
    <html>
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

        <div class="presentation">{
          slides.map((slide) => slide.toXML)
        }</div>
      </body>
    </html>
  }
}
