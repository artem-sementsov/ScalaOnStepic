package part5

import scala.io.StdIn

object IfElseLoops extends App {

  val n = readInt()
  for {
    i <- 1 until n
    j <- 1 until n
  } {
    if (BigInt(i).gcd(j) == 1) println(i + " " + j)
  }
}
