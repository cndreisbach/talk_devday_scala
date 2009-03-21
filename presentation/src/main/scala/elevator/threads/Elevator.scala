package elevator.threads

import scala.actors._

class Elevator(building: Building) extends Actor {
  def act() = {
    var floorNum = 1
    var goingUp = true

    while (true) {
      building.floor(floorNum).elevatorArrives("Arrive at floor = " + floorNum)
      building.floor(floorNum).elevatorLeaves("Leaving floor = " + floorNum)

      Thread.sleep(1000)

      if (floorNum == 1) goingUp = true
      else if (floorNum == building.topFloor) goingUp = false

      floorNum = floorNum + (if (goingUp) 1 else -1)
    }
  }
}