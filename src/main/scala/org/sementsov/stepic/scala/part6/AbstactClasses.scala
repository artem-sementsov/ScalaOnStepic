package org.sementsov.stepic.scala.part6

import scala.io.StdIn

object AbstactClasses extends App {

  task

  object task {

    trait StringProcessor {
      def process(input: String): String
    }

    class TokenDeleter extends StringProcessor {
      override def process(input: String): String = {
        input.replaceAll("\\pP", "")
      }
    }

    class Shortener extends StringProcessor {
      override def process(input: String): String = {
        if (input.length > 20) {
          input.substring(0, 20) + "..."
        } else {
          input
        }
      }
    }

    class ToLowerConvertor extends StringProcessor {
      override def process(input: String): String = {
        input.toLowerCase()
      }
    }

    val tokenDeleter = new TokenDeleter

    val shortener = new Shortener

    val toLowerConvertor = new ToLowerConvertor

    object test1 {
      val str = "This is a Wonderful Test!"

      println(shortener process (tokenDeleter process (toLowerConvertor process str)))

    }

    //test1

    val input = StdIn.readLine()
    println(shortener process (tokenDeleter process (toLowerConvertor process input)))
  }

}
