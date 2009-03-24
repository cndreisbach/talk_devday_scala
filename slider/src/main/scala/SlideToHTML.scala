object SlideToHTML {
  def main(args: Array[String]) {
    (new Slideshow(args(0))).saveHTML
  }
}