object SlideToPDF {
  def main(args: Array[String]) {
    (new Slideshow(args(0))).savePDF
  }
}