
// START dsl
class Trip {
  var startingWaypoint: Waypoint = null
  var currentWaypoint: Waypoint = null
  
  def from(place: String) = {
    startingWaypoint = new Waypoint(place)
    currentWaypoint = startingWaypoint
    this
  }
  
  def to(place: String) = {
    currentWaypoint.next = new Waypoint(place)
    currentWaypoint = currentWaypoint.next
    this
  }
  
// END dsl
    
  def stops: Int = stops(0, startingWaypoint)
  def stops(count: Int, waypoint: Waypoint): Int =
    if (waypoint == null) count else stops(count + 1, waypoint.next)
  
  class Waypoint(from: String) { var next: Waypoint = null }
}
