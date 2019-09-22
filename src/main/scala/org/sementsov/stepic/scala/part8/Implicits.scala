package org.sementsov.stepic.scala.part8

import org.sementsov.stepic.scala.part8.Implicits.task1.Expr.Print

object Implicits extends App {

  task1

  object task1 {

    type Calc[T] = Map[String, T] => T

    trait Expr[T] {
      def literalInt(value: Int): T
      def variable(name: String): T
      def times(x: T, y: T): T
      def plus(x: T, y: T): T
      def minus(x: T, y: T): T = plus(x, negate(y))
      def negate(x: T): T      = times(x, literalInt(-1))
    }

    object Expr {
      final case class Print(s: String, priority: Int, isLit: Boolean = false) {
        def print(outer: Int = 0) = if (outer <= priority) s else s"($s)"
      }

      implicit val stringExpr: Expr[String] = new Expr[String] {
        override def literalInt(value: Int): String = s"$value"
        override def variable(name: String): String = s"${name.toUpperCase}"
        override def times(x: String, y: String): String = s"($x)*($y)"
        override def plus(x: String, y: String): String = s"($x)+($y)"
        override def minus(x: String, y: String): String = s"($x)-($y)"
        override def negate(x: String): String = s"-($x)"
      }

      implicit def numericExpr[T: Numeric]: Expr[Calc[T]] = new Expr[Calc[T]] {
        import Numeric.Implicits._
        val num = implicitly[Numeric[T]]

        override def literalInt(value: Int): Calc[T] = map => num.fromInt(value)
        override def variable(name: String): Calc[T] = map => map(name)
        override def times(x: Calc[T], y: Calc[T]): Calc[T] = map => x.apply(map) * y.apply(map)
        override def plus(x: Calc[T], y: Calc[T]): Calc[T] = map => x.apply(map) + y.apply(map)
      }

      implicit val stringOrderExpr: Expr[Print] = new Expr[Print] {
        import java.lang.Math.min

        override def literalInt(value: Int) = Print(value.toString, 4, isLit = true)
        override def variable(name: String): Print = Print(name.capitalize, 5)
        override def times(x: Print, y: Print): Print = {
          if (x.isLit) f(3, "", x, y)
          else f(3, "*", x, y)
        }
        override def plus(x: Print, y: Print): Print  = f(2, "+", x, y)
        override def minus(x: Print, y: Print): Print = f(2, "-", x, y)
        override def negate(x: Print): Print          = Print(s"-${x.print(1)}", min(1, x.priority))
        private def f(defaultPriority: Int, symbol: String, x: Print, y: Print): Print = {
          Print(s"${x.print(defaultPriority)}$symbol${y.print(defaultPriority)}",
            min3(defaultPriority, x.priority, y.priority))
        }

        def min3(default: Int, left: Int, right: Int): Int = {
          min(default, min(left, right))
        }
      }
    }

    // sugar
    object exprSyntax {
      def literalInt[T](value: Int)(implicit expr: Expr[T]): T = expr.literalInt(value)
      def X[T](implicit expr: Expr[T]): T                      = expr.variable("x")
      def Y[T](implicit expr: Expr[T]): T                      = expr.variable("y")
      def Z[T](implicit expr: Expr[T]): T                      = expr.variable("z")

      implicit class IntToExpr[T](x: Int)(implicit expr: Expr[T]) {
        def lit: T = expr.literalInt(x)
      }

      implicit class NumOps[T](val x: T) extends AnyVal {
        def +(y: T)(implicit expr: Expr[T]): T  = expr.plus(x, y)
        def -(y: T)(implicit expr: Expr[T]): T   = expr.minus(x, y)
        def *(y: T)(implicit expr: Expr[T]): T   = expr.times(x, y)
        def unary_-(implicit expr: Expr[T]): T = expr.negate(x)
      }
    }

    import task1.exprSyntax._

    def function[T: Expr]: T = X * X + 2.lit * (Y + Z * X * 2.lit) - 7.lit * Z + 4.lit
    println(function[String]) // ((((X)*(X))+((2)*((Y)+(((Z)*(X))*(2)))))-((7)*(Z)))+(4)

    println(function[Calc[Double]].apply(Map("x" -> 1, "y" -> -1, "z" -> 0.2))) // 2.4

    println(function[Print].print()) // X*X+2(Y+Z*X*2)-7Z+4
  }

}
