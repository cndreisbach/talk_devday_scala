package elevator.actors

class Building(floorCount: int, passengerCount: int) {
  val floors = new Array[Floor](floorCount)
  val passengers = new Array[Passenger](passengerCount)

  def floor(floorNum: Int) = floors(floorNum - 1)
  def topFloor = floorCount

  def runElevator = {

    for (floorNum <- 1 to floorCount) {
      floors(floorNum - 1) = new Floor(floorNum)
      floors(floorNum - 1).start()
    }
    
    new Elevator(this).start()
        
    for (passengerNum <- 1 to passengerCount) {
      passengers(passengerNum - 1) = new Passenger(this, passengerNum)
      passengers(passengerNum - 1).start()
    }
  }
}
