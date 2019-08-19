package org.sementsov.stepic.scala.part4
import scala.annotation.tailrec

object Fibs extends App{

  @tailrec
  def fibs(index: Int, prev: BigInt = 1, current: BigInt = 0): BigInt = {
    if (index <= 0)
      current
    else {
      fibs(index - 1, prev + current, prev)
    }
  }
  println(fibs(3))
}
