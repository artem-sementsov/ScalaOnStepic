package org.sementsov.stepic.scala.part5

import scala.util.matching.Regex

object PatternMatching extends App {

  {
    case class Pet(name: String, says: String)

    def matching(pet: Pet): String = {
      val robotSays = "(.*[01]+.*)".r
      val kind = pet match {
        case Pet("Rex", _) => "dog"
        case Pet(_, "meow" | "nya") => "cat"
        case Pet(_, robotSays(_)) => "robot"
        case Pet(_, _) => "unknown"
      }

      println(kind)
      kind
    }

    //  require(matching(Pet("Rex", "rrrr")) == "dog")
    //  require(matching(Pet("kitty", "meow")) == "cat")
    //  require(matching(Pet("kit", "nya")) == "cat")
    //  require(matching(Pet("R2D2", "0101010101meow0111010001")) == "robot")

  }

  {
    val nameR: Regex = "([a-zA-Z]{1,})".r
    val emailR: Regex = "(\\w+@)(\\w+\\.\\w+)".r
    val nameEmailR: Regex = (nameR.toString() + " " + emailR.toString()).r

    def testMatching(input: List[String]): Unit = {
      val result: String = input match {
        case List(nameEmailR(name, _, domain), _*) => name + " " + domain
        case List(nameR(head), rest@_*) =>
          val email: String = rest.head
          head + " " + (email match {
            case emailR(parts@_*) => parts.tail.head
            case _ => "invalid"
          })
        case _ => "invalid"
      }
      println(result)
    }

    println("artem".matches(nameR.toString()))
    println("artem@gmail.com".matches(emailR.toString()))
    println("artem artem@gmail.com".matches(nameEmailR.toString()))

    testMatching(List("artem", "artem@gmail.com"))
    testMatching(List("artem artem@gmail.com"))

    testMatching(List("oleg oleg@email.com",
      "7bdaf0a1be3",
      "a8af118b1a2",
      "28d74b7a3fe")
    )
  }
}
