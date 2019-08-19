package org.sementsov.stepic.scala.part6

import java.io.{BufferedReader, InputStreamReader}

object Collections2 extends App {
  /*
    //1
    {
      val list = List(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150)

      list.takeWhile(_ < 100).filter(_ % 4 == 0).map(_ / 4).init.foreach(println)
    }

    //2
    {
      println(Stream.continually(StdIn.readLine).takeWhile(_ != "END").zipWithIndex.collect { case (v, i) if i % 2 == 1 => v.toInt * 2 }.sum)
    }

    //3
    {
      val points: List[Int] = List(1, 3)
      val chr1: List[Char] = "xxxxx".toList
      val chr2: List[Char] = "yyyyy".toList

      def cross(points: List[Int], x: List[Char], y: List[Char]): (List[Char], List[Char]) = points match {
        case Nil => (x, y)
        case head :: tail =>
          cross(tail, x.take(head) ++ y.drop(head), y.take(head) ++ x.drop(head))
      }

      val (h1, h2) = cross(points, chr1, chr2)

      println(h1.mkString)
      println(h2.mkString)
    }
   */

  //4

  import Lesson.field
  import Naval._

  // определить, подходит ли корабль по своим характеристикам
  def validateShip(ship: Ship): Boolean = {
    val height: Int = {
      val heights: List[Int] = ship.map(_._1)
      heights.max - heights.min
    }

    val weight: Int = {
      val weights: List[Int] = ship.map(_._2)
      weights.max - weights.min
    }

    if (height > 0 && weight > 0) false
    else if (height > 3 || weight > 3) false
    else true
  }

  // определить, можно ли его поставить
  def validatePosition(ship: Ship, field: Field): Boolean = {

    // определить что корабль на поле
    ship.forall(inField)

    def noNeighbors(point: Point): Boolean = {
      val (x, y) = point
      val res: Seq[Boolean] = for {
        i <- x - 1 to x + 1
        j <- y - 1 to y + 1
        res = inFieldAndEmpty(i, j)
      } yield res

      res.forall(x => x)
    }

    // point is in field
    def inField(point: Point): Boolean = {
      val (x, y) = point
      if (x >= 0 && x < field.size && y >= 0 && y < field(x).size) true
      else false
    }

    // no other ship at this point
    def isEmpty(point: Point): Boolean = {
      !field(point._1)(point._2)
    }

    // point is out of field or in field and empty
    def inFieldAndEmpty(point: Point): Boolean = {
      !inField(point) || inField(point) && isEmpty(point)
    }

    // определить что у корабля нет прилегающих соседей
    ship.forall(noNeighbors)
  }

  // добавить корабль во флот
  def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = {
    fleet + (name -> ship)
  }

  // пометить клетки, которые занимает добавляемый корабль
  def markUsedCells(field: Field, ship: Ship): Field = ship match {
    case Nil =>
      field
    case head :: tail =>
      val (x, y) = head
      markUsedCells(field.updated(x, field(x).updated(y, true)), tail)
  }

  // логика вызовов методов выше
  def tryAddShip(game: Game, name: String, ship: Ship): Game = {
    val (field, fleet) = game
    if (validateShip(ship)) {

      if (validatePosition(ship, field)) {
        return (markUsedCells(field, ship), enrichFleet(fleet, name, ship))
      }
    }

    game
  }

  def positionFleet(br: BufferedReader): Iterable[String] = {
    var game: Game = (field, Map())

    val shipNumber: Int = br.readLine().toInt
    for (_ <- 0 until shipNumber) {
      val desc: Array[String] = br.readLine().split(" ")
      val name: String = desc(0)
      val length: Int = desc(1).toInt

      var ship: Ship = List()

      Stream.continually(br.readLine()).take(length).foreach(str => {
        val stringPoint: Array[String] = str.split(" ")
        ship = ship.::(stringPoint(0).toInt, stringPoint(1).toInt)
      })

      game = tryAddShip(game, name, ship)
    }

    game._2.keys
  }

  def printField(field: Field): Unit = {
    for (x <- field) {
      for (y <- x) {
        val mark: String = if (y) {
          "\u25A0"
        } else {
          "\u25A1"
        }
        print(mark)
      }
      println()
    }
  }

  val br = new BufferedReader(new InputStreamReader(System.in))

  positionFleet(br).foreach(println)
}
