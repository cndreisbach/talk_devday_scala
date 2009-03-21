package elevator.actors

object Main extends Application {
  private val NUMBER_OF_FLOORS = 6
  private val NUMBER_OF_PASSENGERS = 50
  
  private val building = new Building(NUMBER_OF_FLOORS, NUMBER_OF_PASSENGERS)
  building.runElevator
}
