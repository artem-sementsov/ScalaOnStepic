package part3

import scala.math.BigDecimal.RoundingMode.HALF_UP

import scala.io.StdIn

object NumbersExample extends App{

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


}
