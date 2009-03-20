object HiThere {
  def main(args: Array[String]) {
    val name = if (args.isEmpty) "Developer Day" else args(0)
    println("Hi there, " + name)
  }
}

/*
$ scalac HiThere.scala
$ scala HiThere
Hi there, Developer Day
$ scala HiThere Durham
Hi there, Durham
*/
