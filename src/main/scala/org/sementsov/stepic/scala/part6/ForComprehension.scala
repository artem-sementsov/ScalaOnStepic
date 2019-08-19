package org.sementsov.stepic.scala.part6

object ForComprehension extends App {

  def desc(t: Class[_]): Unit = {println("\n" + t.getSimpleName)}

  task1
  task2
  question2

  object task1 {
    desc(this.getClass)

    val list1 = List(1, 3, 5, 7)
    val list2 = List(2, 4, 6, 8)
    val list3 = List(1, 3, 5, 8, 10, 12, 14)

    for {
      x <- list1
      y <- list2 if y != x
      z <- list3 if x * y == z
    } {
      println(x, y)
    }
  }

  object task2 {
    desc(this.getClass)

    def service1: Either[String, Double] = Right(1.0)

    def service2(res1: Double): Either[String, Int] = Right(1)

    def service3: String = "123"

    def service4(res1: Double, res2: Int, res3: String): Either[String, String] = Right("res")

    def result = {
      for {
        r1 <- service1
        r2 <- service2(r1)
        r4 <- service4(r1, r2, service3)
      } yield r4
    }

    println(result)
  }

  object question2 {
    desc(this.getClass)

    println(for { (k,v) <- Map("a" -> 1, "b" -> 2) } yield k)
    println(for { x <- List(1,2,3,4) } x)
    println(for { x <- Some(1) ; y <- None } yield (x, y))
  }

}
