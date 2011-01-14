package elevator.threads

import scala.actors._
import scala.util.Random

class Passenger(building: Building, passengerNum: Int) extends Actor {
  val generator = new Random(passengerNum)

  def act() = {
    def pickFloorNum() = {
      generator.nextInt(building.topFloor) + 1;
    }
    
    Thread.sleep(generator.nextInt(120 * 1000))

    val comingFrom = pickFloorNum()
    building.floor(comingFrom).waitForElevator(
      this + " waiting at floor " + comingFrom)
    building.floor(comingFrom).getOnOffElevator(
      this + " leaving floor " + comingFrom)
    
    val goingTo = pickFloorNum()
    building.floor(goingTo).waitForElevator(
      this + " on elevator to floor " + goingTo)
    building.floor(goingTo).getOnOffElevator(
      this + " leaving elevator on floor " + goingTo)
    
  }

  override def toString() = "Passenger " + passengerNum
}
