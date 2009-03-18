class CSVLine(line: String) {
  val elements = line.split(",")

  def size = elements.size

  def apply(idx: Int) = {
    elements(idx)
  }
  
  def print {
    println(line)
  }
}
