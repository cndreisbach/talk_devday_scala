package elevator.actors

import scala.actors._
import scala.actors.Actor._

class Elevator(building: Building) extends Actor {
  private var floorNum = 1
  private var goingUp = true
  
  def act() = {
    loop {
      arriveAtFloor()
      leaveFloor()
    }
  }
  
  private def arriveAtFloor() = {
    println("Elevator arrives at floor " + floorNum)
    building.floor(floorNum) ! this
  }
  
  private def leaveFloor(): Unit = {
    receive {
      case "loaded" =>
        println("Elevator leaves floor " + floorNum)
        Thread.sleep(1000)
          
        if (floorNum == 1) goingUp = true
        else if (floorNum == building.topFloor) goingUp = false

        floorNum = floorNum + (if (goingUp) 1 else -1)
      case msg =>
        println("Unhandled message: " + msg)
        leaveFloor()
    }
  }
}