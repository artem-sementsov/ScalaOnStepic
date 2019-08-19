import org.scalatest.FlatSpec
import org.sementsov.stepic.scala.part4.Fibs

class Test extends FlatSpec {

  "fibs" should "return correct results" in {
    assert(Fibs.fibs(0) === 0)
    assert(Fibs.fibs(1) === 1)
    assert(Fibs.fibs(2) === 1)
    assert(Fibs.fibs(3) === 2)
    assert(Fibs.fibs(4) === 3)
    assert(Fibs.fibs(6) === 8)
    assert(Fibs.fibs(10) === 55)
    assert(Fibs.fibs(12) === 144)
  }
}
