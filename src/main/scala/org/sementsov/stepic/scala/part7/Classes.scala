package org.sementsov.stepic.scala.part7

import scala.io.StdIn

object Classes extends App{

  task1

  object task1 {
    class Waiter(name: String, var order: List[String]) {

      println("My name " + name)

      def this(name: String) {
        this(name, List())
      }

      def giveMe(meal: String): Waiter = {
        order = meal :: order
        this
      }

      def complete(): List[String] = {
        order.reverse
      }
    }

    //test1
    {
      val waiter = new Waiter("иван")
      val positions: List[String] = waiter.giveMe("борщ").giveMe("хлеб").complete()
      println(positions)
    }

    //test2
    {
      val input: List[String] = StdIn.readLine().split(" ").toList
      val waiter = new Waiter(input.head)
      input.tail.foreach(waiter.giveMe)
      waiter.complete()
    }
  }
}
