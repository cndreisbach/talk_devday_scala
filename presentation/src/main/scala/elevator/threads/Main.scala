package elevator.threads

object Main extends Application {
  val NUMBER_OF_FLOORS = 6
  val NUMBER_OF_PASSENGERS = 50
  
  println("Hello, world!")
  val building = new Building(NUMBER_OF_FLOORS, NUMBER_OF_PASSENGERS)
  building.runElevator
}
