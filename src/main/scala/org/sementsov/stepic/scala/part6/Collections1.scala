package org.sementsov.stepic.scala.part6

import scala.math._
import scala.util.Random._
import scala.util.Try

object Collections1 {
  def main(args: Array[String]) {

    //1
    {
      val ints: List[Int] = List.fill(nextInt(90) + 10)(nextInt(2))


      println(ints.filter(int => int == 0).mkString(", "))
      println(ints.filter(int => int == 1).mkString(", "))
    }

    //2.1
    {

    }

    //2.2
    {
      def kOrder(list: List[Int], k: Int): Int = {

        def take(list: List[Int], k: Int, elem: Int): Int = k match {
          case 0 =>
            list match {
              case List() => elem
              case head +: tail =>
                take(tail, k, min(head, elem))
            }
          case _ =>
            list match {
              case head +: tail => take(tail, k - 1, max(head, elem))
            }
        }

        take(list.tail, k - 1, list.head)
      }

      require(kOrder(List(3, 8, 1, 12, 23), 4) == 12)
      require(kOrder(List(4, 7, 6, 5, 12, 9, 5), 3) == 5)
      require(Try(kOrder(List(1), 2)).isFailure)
      require(Try(kOrder(List(1), -1)).isFailure)


//      val k: Int = readInt()
//      val list: List[Int] = readLine().split(" ").toList.map(_.toInt)

//      println(kOrder(list, k))
//

    }
  }
}

