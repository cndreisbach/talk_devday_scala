package elevator.actors

import scala.actors._
import scala.util.Random

// START actors
class Passenger(building: Building, passengerNum: Int) extends Actor {
  def act() = {
    val generator = new Random(passengerNum)
    def pickFloor() = 
      building.floor(generator.nextInt(building.topFloor) + 1)

    Thread.sleep(generator.nextInt(120 * 1000))

    val comingFrom = pickFloor()
    val goingTo = pickFloor()
    comingFrom ! (this, goingTo)
  }
// END actors

  override def toString() = "Passenger " + passengerNum
}
