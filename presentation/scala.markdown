# Scala: A Modern Language

Clinton R. Nixon

Viget Labs

---

# What's Scala?

* Created in 2001 by Martin Odersky
* Runs on the JVM
* Everything's an object
* But it's a functional language
* Compiled beforehand or just-in-time
* Static typing with inference
* Actor-based concurrency

---

# Hello world

<pre class="code brush:scala" src="src/main/scala/HiThere.scala" />

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
* No primitives
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

<pre class="brush:scala" src="src/main/scala/CSVLine.scala" />

Only non-obvious cases need explicit typing.

---

# Running a script

<pre class="brush:scala" src="src/scripts/encrypt.scala" />

---

# Syntax made for DSLs

Methods can be called in one of two ways:

* `object.method(argument)`
* `object method argument`

Everything's a object, so this is how arithmetic works.

NEED EXAMPLE

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

<pre class="brush:scala" src="../slider/src/main/scala/Slideshow.scala" section="immutability" />

* `val` and `var` are used to declare variables
* `val` = immutable value
* `var` = mutable variable

Immutable variables simplify concurrency tremendously. I find they help me
eliminate bugs, as well.

---

# First-class functions

<pre class="brush:scala" src="../slider/src/main/scala/Slideshow.scala" section="functions" />

The benefit of first-class functions cannot be emphasized enough. Functions are objects, complete with literal syntax and the ability to be returned from other functions.

This is what makes Ruby, JavaScript, and Scheme awesome (among other things.)

---

# Tail recursion

<pre class="brush:scala" src="src/main/scala/Recursor.scala" />

---

# Tail recursion, tested

<pre class="brush:scala" src="src/test/scala/RecursorTest.scala" />

---

# Pattern matching

Pattern matching is the `case` statement bitten by a radioactive lambda.

Pattern matching on tests:

<pre class="brush:scala" src="../slider/src/main/scala/OptionParser.scala" section="matching" />

---

# Pattern matching

Pattern matching on equivalence:

<pre class="brush:scala" src="../slider/src/main/scala/Slide.scala" section="matching" />

---

# Scala is an object-oriented language

* Classes and instances
* Stand-alone objects
* Companion objects
* Selective mutability
* Parameter-less methods
* Abstract classes
* Traits
* No primitives
+ Generics

---

# Other topics

* The ecosystem
* How does Scala interact with other JVM languages?
* XML Literals
+ What is ScalaCheck?
* Why is Scala good for writing DSLs? (df format now)
+ What are case classes?
+ What is combinator parsing?
* What are actors and messages?
* How do actors and messages help with concurrency?
* Could you show me this with an example?
