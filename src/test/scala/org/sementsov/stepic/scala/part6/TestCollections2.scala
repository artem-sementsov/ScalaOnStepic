package org.sementsov.stepic.scala.part6

import java.io.{BufferedReader, ByteArrayInputStream, InputStreamReader}

import org.scalatest._
import org.sementsov.stepic.scala.part6.Collections2._

class TestCollections2 extends FlatSpec {

  "validateShip" should "approve correct ship" in {
    assert(validateShip(List((0, 0), (0, 1))))
  }

  "validateShip" should "refuse incorrect ship" in {
    assert(!validateShip(List((0, 0), (0, 1), (1, 1))))
    assert(!validateShip(List((0, 0), (0, 1), (0, 2), (0, 3), (0, 4))))
  }

  "validatePosition" should "approve correct position" in {
    assert(validatePosition(List((0, 0)), Vector.fill(1, 1)(false)))
  }

  "validatePosition" should "refuse incorrect position" in {
    assert(!validatePosition(List((0, 0)), Vector.fill(1, 1)(true)))
    assert(!validatePosition(List((0, 0)), Vector(Vector(false, false), Vector(false, true))))
  }

  "enrichFleet" should "add ship into fleet" in {
    assert(enrichFleet(Map(), "ship", List((0, 0))) == Map("ship" -> List((0, 0))))
  }

  "markUsedCells" should "mark cells with small ship" in {
    assert(markUsedCells(Vector.fill(1, 1)(false), List((0, 0))) == Vector.fill(1, 1)(true))
  }

  "markUsedCells" should "mark cells with big ship" in {
    assert(
      markUsedCells(Vector.fill(4, 4)(false), List((1, 0), (1, 1), (1, 2), (1, 3))) == Vector(
        Vector(false, false, false, false),
        Vector(true, true, true, true),
        Vector(false, false, false, false),
        Vector(false, false, false, false)
      )
    )
  }

  "tryAddShip" should "add ship into empty field" in {
    assert(
      tryAddShip((Vector.fill(1, 1)(false), Map()), "ship", List((0, 0)))
        == (Vector.fill(1, 1)(true), Map("ship" -> List((0, 0))))
    )
  }

  "positionFleet" should "position ships from sample 1" in {

    val inputString: String =
      """2
        |MillenniumFalcon 4
        |2 5
        |3 5
        |4 5
        |5 5
        |Varyag 1
        |9 9
      """.stripMargin

    val br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())))

    assert(positionFleet(br).toList == List("MillenniumFalcon", "Varyag"))
  }

  "positionFleet" should "position ships from sample 2" in {

    val inputString: String =
      """3
        |BlackPearl 3
        |1 6
        |1 7
        |1 8
        |MillenniumFalcon 4
        |2 5
        |3 5
        |4 5
        |5 5
        |Varyag 1
        |9 9
      """.stripMargin

    val br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())))

    assert(positionFleet(br).toList == List("BlackPearl", "Varyag"))
  }

}
