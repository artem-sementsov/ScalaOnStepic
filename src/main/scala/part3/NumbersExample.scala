package part3

import scala.math.BigDecimal.RoundingMode.HALF_UP

import scala.io.StdIn

object NumbersExample extends App {

  def crispsWeight(weight: BigDecimal, potatoWaterRatio: Double, crispsWaterRatio: Double): BigDecimal = {
    (weight * (1 - potatoWaterRatio) / (1 - crispsWaterRatio)).setScale(5, HALF_UP)
  }

  //println(crispsWeight(90.0, 0.9, 0.1))

  //println(readInt().toBinaryString.count(char => char == '1'))

  //  {val s3 = "bar"; val s1 = "foo" + s3; val s2 = "foo" + s3; println(s1 == s2)}
  //  {val s1 = "foo"; val s2 = "foo"; println(s1 == s2)}
  //  {val s1 = "foo"; val s2 = "foo"; println(s1 eq s2)}
  //  {val s3 = "bar"; val s1 = "foo" + s3; val s2 = "foo" + s3; println(s1 eq s2)}

  def isCapital(word: String, pos: Int): Boolean = {
    word(pos).isUpper
  }

  //println(isCapital("Scala", 0))

  //  val indexes = readLine()
  //  val startIndex = indexes.split(" ")(0).toInt
  //  val endIndex = indexes.split(" ")(1).toInt
  //  val str = readLine()
  //  println(str.substring(0, startIndex) + str.substring(startIndex, endIndex + 1). reverse + str.substring(endIndex + 1))

  /*
   Требовая snake-case к строке:

Должна содержать только строчные латинские буквы и символ подчёркивания
Символ подчёркивания не должен стоять в начале и в конце строки
Два символа подчёркивания не могут стоять рядом
 Считайте из стандартного потока ввода строку и напечатайте true, если она удовлетворяет требованиям выше, false ﻿иначе.
   */

//  val str = readLine()
//  if (str.matches("^[a-z_]*$") && !str.startsWith("_") && !str.endsWith("_") && !str.matches(".*_{2}.*")) println(true)
//  else println(false)

//  val regex = "A-z_".r
//  for (str: String <- ("snake_case" :: "_qwe" :: "ert_" :: "asd__asd_a" :: Nil)) {
//    println(str.matches("^[a-zA-Z_]*$"))
//    println(!str.startsWith("_"))
//    println(!str.endsWith("_"))
//    println(!str.matches(".*_{2}.*"))
//    println("\n")
//  }

//  def fibs(num: Int): Int = {
  //    if (num == 1) 1
  //    else if (num == 2) 1
  //    else fibs(num - 1) + fibs(num - 2)
  //  }

//  def getGift(): Int = 100
//
//  def sendGift(currentAmount: Int, gift: Int) = {
//    if (currentAmount >= 500)
//      currentAmount + gift
//    else
//      currentAmount
//  }
//
//  val accountAmounts = List(100, 200, 500, 300, 700)
//
//  for (account <- accountAmounts) {
//    sendGift(account, getGift)
//  }

  object LessonData{
    def searchInArray(cond: Int => Boolean, array: List[Int]): List[Int] = {
      array.filter(cond)

    }
  }

  val searchOdd = (list: List[Int]) =>  LessonData.searchInArray(x => x % 2 == 1, list)

  println(searchOdd(List(8,11,12))) // List(11)
}
