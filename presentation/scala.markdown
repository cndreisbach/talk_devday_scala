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
* No obvious constructors
* Syntax is close to what you're used to, but not close enough
* Relies on another language (Java) for much of its standard library

---

# Type inference

<pre class="brush:scala" src="src/main/scala/CSVLine.scala" />

Only non-obvious cases need explicit typing.

---

# Type inference doesn't work with recursion

<pre class="brush:scala" src="../slider/src/main/scala/Slide.scala" section="recursive" />

---

# Scala is a functional language

* Immutability
* Anonymous, first-class functions
* Nested functions
* Tail recursion
* Lists
* Pattern matching
+ Currying
+ For comprehensions
+ Lazy values

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

* How does Scala interact with other JVM languages?
* XML Literals
+ What is ScalaCheck?
* Why is Scala good for writing DSLs? (df format now)
+ What are case classes?
+ What is combinator parsing?
* What are actors and messages?
* How do actors and messages help with concurrency?
* Could you show me this with an example?
