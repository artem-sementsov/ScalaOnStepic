package part5

import sun.awt.AWTAccessor.InputEventAccessor

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
    def testMatching(input: List[String]) = {
      val nameR = "([a-zA-Z]{1,})".r
      val nameEmailR = "([a-zA-Z]+) (\\w+@)(\\w+\\.\\w+)".r
      val emailR = "(\\w+@)(\\w+\\.\\w+)".r
      val result = input match {
        case List(nameR(str), rest@_*) => {
          val email = rest.head
          println(email)
          str + " " + email match {
            case emailR(parts@_*) => parts.tail.head
            case _ => "invalid1"
          }
        }
        case List(nameEmailR(parts@_*)) => parts.head + " " + parts.tail.tail.head
        case _ => "invalid"
      }
      println(result)
    }
    println("artem".matches("([a-zA-Z]{1,})"))
    println("artem@gmail.com".matches("(\\w+@)(\\w+\\.\\w+)"))
    println(testMatching(List("artem", "artem@gmail.com")))
    println(testMatching(List("artem artem@gmail.com")))

  }
}
