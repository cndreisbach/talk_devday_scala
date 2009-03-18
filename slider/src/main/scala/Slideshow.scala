class Slideshow(filename: String) {
  val file = new File(filename)
  val fileSrc = Source.fromFile(file)
  
}