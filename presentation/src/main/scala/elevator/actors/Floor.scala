package elevator.actors

import scala.actors._
import scala.actors.Actor._

// START actors
class Floor(floorNum: Int) extends Actor {
  private var passengersWaitingToGetOn = List[(Passenger, Floor)]()
  private var passengersWaitingToGetOff = List[Passenger]()
  
  def act() = {
    loop {
      react {
        case (passenger: Passenger, floor: Floor) =>
          passengersWaitingToGetOn = (passenger, floor) :: passengersWaitingToGetOn
          println(passenger + " waiting for elevator at " + this)
// END actors          
        case (passenger: Passenger) =>
          passengersWaitingToGetOff = passenger :: passengersWaitingToGetOff
          println(passenger + " on elevator to " + this)

        case (elevator: Elevator) =>
          for (passenger <- passengersWaitingToGetOff) {
            println(passenger + " getting off at " + this)
          }
          passengersWaitingToGetOff = List[Passenger]()

          for ((passenger, floor) <- passengersWaitingToGetOn) {
            println(passenger + " getting on at " + this)
            floor ! passenger
          }
          passengersWaitingToGetOn = List[(Passenger, Floor)]()

          elevator ! "loaded"
        case msg =>
          println("Unhandled message: " + msg)
      }
    }
  }

  override def toString() = "Floor " + floorNum
}
