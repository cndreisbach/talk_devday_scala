# Scala: A Modern Language

Clinton R. Nixon

Viget Labs

---

# What's Scala?

* Created in 2001 by Martin Odersky
* Runs on the JVM
* Compiled beforehand or just-in-time
* It's a functional language
* But everything's an object
* Actor-based concurrency

---

# Hello world

<pre class="code brush:scala" src="src/main/scala/HiThere.scala"></pre>

---

# Why am I interested in Scala?

## I'm a Javan:

* Scala classes are Java classes. Scala's just another Java library as far as the JVM's concerned.
* I like functional programming and want to use it.
* I want something more high-level, but don't want to sacrifice speed or static typing.
* Writing concurrent Java code makes me want to break albums of Beethoven
across the back of the john

---

# Why am I interested in Scala?

## I'm a Rubyist:

* I want something fast to write parts of my code in, but I don't want to sacrifice readability or dynamism.
* I need to write some concurrent code and
  * I want some actual threads
  * I want to not scream
* By definition, I'm a developer that likes emerging technologies, and I want to learn something new

---

# What is different about Scala?

Most of Scala isn't jarring.

* No class or static attributes or methods
* No primitives; everything is an object
* No obvious constructors
* No ternary operator
* Syntax is close to what you're used to, but not close enough
* Relies on another language (Java) for much of its standard library

---

# Scala is a scripting language

* REPL
* Light ceremony
* Type inference
* DSL-able syntax

---

# Light ceremony in the REPL

<pre class="brush:scala code">
scala> var pets = Map("dogs" -> List("Charlie", "Eli"), "cats" -> List("Violet"))
pets: scala.collection.immutable.Map[java.lang.String,List[java.lang.String]] = Map(dogs -> List(Charlie, Eli), cats -> List(Violet))

scala> pets("dogs").first                                                        
res2: java.lang.String = Charlie

scala> pets += ("unicorns" -> List("Spartacus"))

scala> pets
res5: scala.collection.immutable.Map[java.lang.String,List[java.lang.String]] = Map(dogs -> List(Charlie, Eli), cats -> List(Violet), unicorns -> List(Spartacus))
</pre>

---

# Type inference

<pre class="brush:scala" src="src/main/scala/CSVLine.scala"></pre>

Only non-obvious cases need explicit typing.

---

# Running a script

<pre class="brush:scala" src="src/scripts/encrypt.scala"></pre>

---

# Light syntax

* Type inference
* Semicolon inference
* Case classes give syntax like list and hashmap literals
* Parameter-less methods called like object values (`Foo.count` instead of `Foo.count()`)
* Closures used like Ruby blocks

---

# Syntax made for DSLs

Methods can be called in one of two ways:

* `object.method(argument)`
* `object method argument`

Everything's a object, so this is how arithmetic works.

---

# An object created to make a DSL

<pre class="brush:scala" src="src/main/scala/Trip.scala" section="dsl"></pre>

---

# The DSL at work

<pre class="brush:scala" src="src/test/scala/TripTest.scala" section="dsltest"></pre>

---

# Scala is a functional language

* Immutability
* Anonymous, first-class functions
* Nested functions
* Tail recursion
* Lists
* Pattern matching
+ For comprehensions
+ Lazy values

---

# Immutability

<pre class="brush:scala" src="../slider/src/main/scala/Slideshow.scala" section="immutability"></pre>

* `val` and `var` are used to declare variables
* `val` = immutable value
* `var` = mutable variable

Immutable variables simplify concurrency tremendously. I find they help me
eliminate bugs, as well.

---

# First-class functions

<pre class="brush:scala" src="../slider/src/main/scala/Slideshow.scala" section="functions"></pre>

The benefit of first-class functions cannot be emphasized enough. Functions are objects, complete with literal syntax and the ability to be returned from other functions.

This is what makes Ruby, JavaScript, and Scheme awesome (among other things.)

---

# Tail recursion

<pre class="brush:scala" src="src/main/scala/Recursor.scala"></pre>

---

# Tail recursion, tested

<pre class="brush:scala" src="src/test/scala/RecursorTest.scala"></pre>

---

# Pattern matching

Pattern matching is the `case` statement bitten by a radioactive lambda.

Pattern matching on tests:

<pre class="brush:scala" src="../slider/src/main/scala/OptionParser.scala" section="matching"></pre>

---

# Pattern matching

Pattern matching on equivalence:

<pre class="brush:scala" src="../slider/src/main/scala/Slide.scala" section="matching"></pre>

---

# Scala is an object-oriented language

* Classes and instances
* Stand-alone objects
* Companion objects
* Selective immutability
* Abstract classes
* Traits

---

# Stand-alone and companion objects

<pre class="brush:scala" src="../slider/src/main/scala/Slideshow.scala" section="object"></pre>

---

# Data structures

Most data structures are available in immutable and mutable flavors, and do the intelligent thing with similar methods.

<pre class="brush:scala">
scala> val mutableMap = scala.collection.mutable.Map("Durham" -> "awesome")

scala> mutableMap.update("Columbus", "not so great")                           

scala> mutableMap
res7: scala.collection.mutable.Map[java.lang.String,java.lang.String] = Map(Columbus -> not so great, Durham -> awesome)

scala> val immutableMap = scala.collection.immutable.Map("Durham" -> "awesome")

scala> immutableMap.update("Columbus", "not so great")                         
res8: scala.collection.immutable.Map[java.lang.String,java.lang.String] = Map(Durham -> awesome, Columbus -> not so great)
</pre>

---

# Traits

<pre class="brush:scala" src="src/test/scala/CSVLineTest.scala" section="test"></pre>

Traits are like both Java interfaces and Ruby mixin modules. They can contain both concrete and abstract values and methods.

---

# Multiple traits and traits at instantiation

<pre class="brush:scala" src="src/scripts/dogs.scala"></pre>

---

# Other topics

* The ecosystem
* How does Scala interact with other JVM languages?
* XML Literals
+ What are case classes?
+ What is combinator parsing?
+ What is ScalaCheck?
* What are actors and messages?
* How do actors and messages help with concurrency?
* Could you show me this with an example?
