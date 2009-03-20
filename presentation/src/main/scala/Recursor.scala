object Recursor {  
  def recurseTailCall(i: Int) {
    if (i > 10)
      throw new Exception
    else
      recurseTailCall(i + 1)
  }

  def recurseNonTailCall(i: Int):Int = {
    if (i > 10)
      throw new Exception
    else {
      val j = recurseNonTailCall(i + 1)
      j
    }
  }
}
