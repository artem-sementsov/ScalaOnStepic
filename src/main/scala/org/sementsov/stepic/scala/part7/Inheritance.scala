package org.sementsov.stepic.scala.part7

object Inheritance extends App {

  task2

  object task1 {
    trait Animal {
      val sound: String
      def voice: Unit = println("voice: " + sound)
    }

    case class Cat() extends Animal {
      override val sound: String = "Meow!"
    }

    case class Dog() extends Animal {
      override val sound: String = "Woof!"
    }

    case class Fish() extends Animal {
      override val sound: String = ""

      override def voice: Unit = println("Fishes are silent!")
    }

    val animals = Seq(new Cat, new Dog, new Fish)
    animals.foreach(_.voice)

  }

  object task2 {

    trait AbstractBank {
      def a: Char
      def b: Char
      def c: Char
      def d: Char
      def e: Char
      def f: Char
      def issueCredit(): Unit
    }

    trait BankA extends AbstractBank {
      override val b = 'T'
      override val d = 'R'
      override val f = 'I'
    }

    trait BankB extends AbstractBank {
      override val a = 'E'
      override val f = 'D'
    }

    trait BankC extends AbstractBank {
      override val b = 'C'
      override val d = 'D'
    }

    trait BankD extends AbstractBank {
      override val b = 'C'
      override val c = 'R'
      override val d = 'E'
    }

    trait BankE extends AbstractBank {
      override val b = 'C'
      override val a = 'I'
      override val e = 'T'
    }

    class CreditBank extends BankD with BankB with BankE{
      def issueCredit() = println("" + b + c + d + f + a + e) //for example: a + b + c + d + e + f
    }

    private val bank = new CreditBank
    bank.issueCredit()
  }

}

trait A
abstract class B
class C
object D
case class E()

//case class F() extends E
