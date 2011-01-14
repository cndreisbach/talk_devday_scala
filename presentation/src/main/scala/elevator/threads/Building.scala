package elevator.threads

class Building(floorCount: Int, passengerCount: Int) {
  val floors = new Array[Floor](floorCount)
  val passengers = new Array[Passenger](passengerCount)

  def floor(floorNum: Int) = floors(floorNum - 1)

  def topFloor = floorCount

  def runElevator = {
    for (floorNum <- 1 to floorCount) {
      floors(floorNum - 1) = new Floor
    }
    
    new Elevator(this).start()
        
    for (passengerNum <- 1 to passengerCount) {
      passengers(passengerNum - 1) = new Passenger(this, passengerNum)
      passengers(passengerNum - 1).start()
    }
  }
}
