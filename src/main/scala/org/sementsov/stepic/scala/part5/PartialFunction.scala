package org.sementsov.stepic.scala.part5

object PartialFunction extends App {

  case class Jar(name: String, value: Int, price: Double)

  def discount: PartialFunction[Jar, String] = {
    case Jar(name, value, price) if value > 10 => name + " " + price * 0.1
    case Jar(name, value, price) if value >= 5 => name + " " + price * 0.05
  }

  List(
    Jar("Морской синий 6л", 6, 3000.0),
    Jar("Огненно-красный 12л", 12, 5000.0),
    Jar("Зеленый 1л", 1, 500.0)
  )

  val jars = List(Jar("Морской синий 6л", 6, 3000), Jar("Огненно-красный 12л", 12, 5000))
  println(jars.collect(discount))

  {
    def swap3(tuple: (Int, Int, Int)): (Int, Int, Int) = tuple match {
      case (a, b, c) => (c, b, a)
    }

    println(swap3((1, 2, 3)))
    //    require(swap3((1, 2, 3)) == (3, 2, 1))
  }

  {

    def foo(list: List[Int]): Int = {
      list.find(x => x % 3 == 0).map(x => x * 2).getOrElse(0)
    }

    require(foo(List(1, 2, 3, 4, 5, 6)) == 6)
  }

  {

    def bar: Int => Option[Int] = ???
    val x: Option[Int] = Some(10)

    println(x.map(x => bar(x)).getOrElse(None))
  }

}
