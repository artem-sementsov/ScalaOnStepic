package org.sementsov.stepic.scala.part8

object Variation extends App {

  object test {
    val v: Any = null

    v match {
      case s: String => println(s.getBytes)
      case _ => println("other")
    }

    println("a".getBytes().mkString("_"))

    assert(Array.emptyByteArray sameElements "".getBytes())

    private val bytes: Array[Byte] = "".getBytes()

    show(null, "desc: null")
    show(Array(), "desc: empty")
    show(Array(null), "desc: array of null")
    show(Array(Array.emptyByteArray), "desc: array of empty")

    def show(data: Array[Array[Byte]], desc: String): Unit = {
      println(desc)
      data match {
        case nul if nul == null => println("data is null")
        case empty if empty.isEmpty => println("data is empty")
        case nonEmpty => nonEmpty.foreach {
          case a if a == null => println("content of data is null")
          case arr => println(arr.map(_.toChar).mkString)
        }
      }
    }
  }

  //  task1
  task2

  object task1 {

    case class A(value: String)

    class B(override val value: String) extends A(value)

    object B {
      def apply(value: String): B = new B(value)
    }

    val objB = B("It is a B.value")
    val objA = A("It is a A.value")

    def methodPrint[C <: A](f: FunctionPrint[C], obj: C): Unit = {
      f(obj)
    }

    class FunctionPrint[T <: A](prefix: String) {
      def apply(t: T): Unit = println(prefix + " " + t.value)
    }

    object FunctionPrint {
      def apply[T <: A](prefix: String) = new FunctionPrint[T](prefix)
    }

    val printA: FunctionPrint[A] = FunctionPrint[A]("A-value:")
    val printB: FunctionPrint[B] = FunctionPrint[B]("B-value:")

    methodPrint(printB, objB)
    methodPrint(printA, objB)
  }

    object task2 {
      class Person (val name: String)

      class Student(name: String, val course: Int)
        extends Person(name)

      case class Pair[+T](first: T, second: T) {
        def replaceFirst[U >: T](newValue: U): Pair[U] = {
          Pair(newValue, second)
        }
      }

      def printNames[T <: Person](pair: Pair[T]): Unit = {
        println("1: " + pair.first.name + "  2: " + pair.second.name)
      }

      val pair = Pair(new Student("Pavel", 1), new Student("Oleg", 5))
      printNames(pair)
      private val value: Pair[Person] = pair.replaceFirst(new Person("Oliver"))
      printNames(pair.replaceFirst(new Person("Oliver")))
    }

}

