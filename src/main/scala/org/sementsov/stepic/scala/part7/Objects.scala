package org.sementsov.stepic.scala.part7

import scala.io.StdIn
import scala.util.matching.Regex

object Objects extends App {

  //task1
  task2

  object task1 {

    case class Point(x: Double, y: Double, z: Double) {
      def show = s"$x $y $z"
    }

    object Point {

      def apply(str: String): Point = {
        val coordinates: Array[String] = str.split(" ")
        new Point(coordinates(0).toDouble, coordinates(1).toDouble, coordinates(2).toDouble)
      }

      def average(list: List[Point]): Point = list match {
        case Nil => Point(0, 0, 0)
        case _ => {
          val length = list.length
          val (xs, ys, zs) = list.unzip3(point => (point.x, point.y, point.z))
          Point(xs.sum / length, ys.sum / length, zs.sum / length)
        }
      }

      def show(point: Point): String = {
        point.show
      }
    }

    test1
    test2

    object test1 {
      val in1: String = "1 2.5 4"
      val in2: String = "4 3.5 6"

      println(Point(in1))

      val p1 = Point.apply(in1)
      val p2 = Point.apply(in2)

      val point: Point = Point.average(List(p1, p2))

      println(Point.show(point))
    }

    object test2 {
      val in1: String = "1 2 3"
      val in2: String = "4 5 6"
      val in3: String = "7 8 9"

      val p1 = Point.apply(in1)
      val p2 = Point.apply(in2)
      val p3 = Point.apply(in3)

      val point: Point = Point.average(List(p1, p2, p3))

      println(Point.show(point))
    }

  }

  object task2 {

    object FacedString {

      val pattern: Regex = "^\\*_\\*(.*)\\*_\\*$".r

      def apply(input: String) = s"*_*$input*_*"

      def unapply(arg: String): Option[String] = {
        arg match {
          case pattern(string) => Some(string)
          case _ => None
        }
      }
    }

    StdIn.readLine() match {
      case FacedString(s) => println(s)
      case _ => println("Could not recognize string")
    }
  }

  case class Dog(name: String, breed: String, ownerName: String)
  val bobik = Dog("Bobik", "corgie", "Oleg")

  val t1 = bobik.copy("Tuzik")
  val t2 = bobik.copy(name="Tuzik")
  val t3 = Dog("Tuzik", "corgie", "Oleg")

  trait Node
  case class Leaf(value: String) extends Node
  case class Branch(left: Node, right: Node) extends Node

  def matching(node: Node, value: String): Node = node match {
    case leaf : Leaf => {
      Branch(leaf, Leaf(value))
    }
    case branch: Branch => {
      branch
    }
  }

  matching(Leaf("one"), "two")


}
