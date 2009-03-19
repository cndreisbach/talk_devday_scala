object Slider {
  def create(filename: String) {
    val slideshow = new Slideshow(filename)
    slideshow.saveHTML()
  }
  
  def main(args: Array[String]) {
    var concurrent = false

    val opts = new OptionParser(false)
    opts.separator("scala Slider [opts] source_file")
    opts.separator("Slider options:")
    // opts.onInt("-n","--total-jobs","How many jobs to split the  
    // JobDescription into", {x: Int => totalJobs = x})
    // opts.onInt("-i","--iteration","Run exactly this iteration (and then  
    // stop)", {x: Int => specificIteration = Some(x) })
    // opts.separator("")
    opts.on("-c", "--concurrent", "Build the slides concurrently", {concurrent = true})
    opts.separator("")
    opts.help("-h", "--help")
    val leftoverArgs = opts.parse(args)
    
    if (leftoverArgs.size != 1) {
      opts.showUsage
    } else {
      println(new Slideshow(leftoverArgs(0)))
    }
  }
}
