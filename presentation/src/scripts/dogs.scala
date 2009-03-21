class Dog { def bark = "WOOF" }
trait Ninja { def sneak = "..." }
trait Beggar { def beg = "MORAWR" }
class NinjaDog extends Dog with Ninja { }

val dog = new Dog
val ninjaDog = new NinjaDog
val myDog = new Dog with Ninja with Beggar

println(dog.bark)
println(ninjaDog.sneak)
println(myDog.beg)
println(myDog.sneak)

// WOOF
// ...
// MORAWR
// ...
