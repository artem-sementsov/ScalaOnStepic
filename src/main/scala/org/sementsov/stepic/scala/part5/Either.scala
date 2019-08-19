package org.sementsov.stepic.scala.part5

import scala.math._

object Either extends App {

  {
    println(Right(1).flatMap(_ => Left(2)).flatMap(_ => Left(3)))
  }

  {
    def divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)] = {
      if (!isProper(p) || !isProper(q)) Left("Invalid input")
      else Right((p._1 * q._2, p._2 * q._1))
        .filterOrElse(!isZero(_), "Zero divisor")
        .filterOrElse(isProper, "Improper result")
        .map(divideByGcd)
    }

    def isProper(a: (Int, Int)): Boolean = a match {
      case (n: Int, d: Int) => abs(n) <= abs(d)
    }

    def isZero(a: (Int, Int)): Boolean = a match {
      case (_: Int, d: Int) => d == 0
    }

    def gcd(a: Int, b: Int): Int = {
      if (b == 0) a else gcd(b, a % b)
    }

    def divideByGcd(a: (Int, Int)): (Int, Int) = {
      val g: Int = gcd(a._1, a._2)
      a._1 / g -> a._2 / g
    }

    println(divide((1, 2))((0, 3)))
  }

  {
    def flatten(options: List[Option[Int]]): List[Int] = {
      options collect { case Some(a) => a }
    }

    val list: List[Option[Int]] = List(Some(1), None, Some(2))
    println(flatten(list))
  }
}
