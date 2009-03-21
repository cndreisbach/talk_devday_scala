import scala.io.Source
import scala.xml._
import com.petebevin.markdown.MarkdownProcessor
import org.apache.commons.io.FilenameUtils

class Slide(slideshow: Slideshow, text: String) {
  val mdProc = new MarkdownProcessor

  def toXML = {
    processDirectives(
      XML.loadString("<div class='slide'>" + mdProc.markdown(text) + "</div>"))
  }

  private def processDirectives(slideXML: Node): Node = {
    // START matching
    slideXML match {
      case <pre /> => 
        includeCode(slideXML)
      case Elem(_, _, _, _, c @ _ *) =>
        Elem(slideXML.prefix, 
             slideXML.label,
             slideXML.attributes,
             slideXML.scope,
             c.map((child) => processDirectives(child)): _*) // recursive
      case _ => slideXML
    }
    // END matching
  }

  private def includeCode(code: Node) = {
    val src = code attribute "src"
    val section = code attribute "section"
    <div class="include_code">
      <pre class={codeClass(code attribute "class")}>{ codeText(src, section) }</pre>
      <a class="code_link" href={ "txmt://open?url=file://" + codeSrc(src) }>source</a>
    </div>
  }

  private def codeClass(htmlClass: Option[Seq[Node]]) = {
    if (htmlClass.isDefined)
      htmlClass.get.toString + " code"
    else
      "code"
  }

  private def codeSrc(src: Option[Seq[Node]]) = {
    require(src.isDefined)
    FilenameUtils.concat(slideshow.path, src.get.toString)
  }

  private def codeText(src: Option[Seq[Node]], section: Option[Seq[Node]]) = {
    require(src.isDefined)

    // TODO remove minimum whitespace
    val text = Source.fromFile(codeSrc(src)).getLines.toList.reduceLeft((a, b) => a + b)

    if (section.isDefined) {
      text.split(
        "[\r\n]+[^\r\n]*START " + section.get.toString + "[^\r\n]*[\r\n]+", 2
      )(1).split(
        "[\r\n]+[^\r\n]*END " + section.get.toString + "[^\r\n]*[\r\n]+", 2
      )(0)
    } else {
      text
    }
  }
}
