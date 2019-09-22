package org.sementsov.stepic.scala.part8

object GenericTypes extends App {

  task1
  task2

  object task1 {
    case class Pair[T, S](first: T, second: S) {
      def swap(): Pair[S, T] = {
        Pair(second, first)
      }
    }

    test1

    object test1 {
      val pair: Pair[String, Int] = Pair(123, "Oleg").swap()
      require(pair.first ==  "Oleg")
      require(pair.second == 123)
    }
  }

  object task2 {
    case class Pair[T <% Ordered[T]](first: T, second: T) {
      def smaller: T =
        if (first < second) first
        else second
    }

    test1

    object test1 {
      val p = Pair(BigInt("1000000000000000"),BigInt("7000000000000000"))
      require(p.smaller == BigInt("1000000000000000"))
    }
  }

  case class IntContainer[F[_]](value: F[Int])
}
