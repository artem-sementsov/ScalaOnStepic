import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import part4.Fibs

@RunWith(classOf[JUnitRunner])
class Test extends FunSuite {

  test("getFibonacci") {
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
