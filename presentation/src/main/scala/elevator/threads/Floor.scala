package elevator.threads

// START threads
class Floor {
  var loading = false
  var passengersWaiting = 0
  def waiting = passengersWaiting > 0

  def waitForElevator(msg: String) = synchronized {
    while (loading) wait()

    passengersWaiting += 1
    println(msg)

    notify()
  }
// END threads

  def getOnOffElevator(msg: String) = synchronized {
    while (!loading) wait()

    passengersWaiting -= 1
    println(msg)

    notify()
  }

  def elevatorArrives(msg: String) = synchronized {
    while (loading) wait()

    loading = waiting
    println(msg)
    
    notify()
  }

  def elevatorLeaves(msg: String) = synchronized {
    while (waiting) wait()

    loading = false
    println(msg)

    notify()
  }
}
