package org.sementsov.stepic.scala.part8

import org.sementsov.stepic.scala.part8.Types.task1.Vect.Aux

object Types extends App {

  task1

  object task1 {

    trait Vect extends Any {
      type Item

      def length: Int

      def get(index: Int): Item

      def set(index: Int, item: Item): Aux[Item]
    }

    object Vect {
      type Aux[I] = Vect {type Item = I}
    }

    final case class StringVect(str: String) extends AnyVal with Vect {
      type Item = Char

      def length = str.length

      def get(index: Int) = str.charAt(index)

      def set(index: Int, item: Char): Aux[Char] = StringVect(str.updated(index, item))
    }

    final case class BoolVect64(values: Long) extends AnyVal with Vect {
      type Item = Boolean

      def length = 64

      def get(index: Int): Item = (values >> index) & 1

      def set(index: Int, item: Boolean): Aux[Boolean] = {
        val resetToZero = values & ~(1L << index)
        BoolVect64(resetToZero | (item << index))
      }
    }

    final case class BoolVect8(values: Byte) extends AnyVal with Vect {
      type Item = Boolean

      def length = 8

      def get(index: Int): Item = (values >> index) & 1

      def set(index: Int, item: Boolean): Aux[Boolean] = {
        val resetToZero: Int = values & ~(1 << index)
        BoolVect8(((resetToZero | (item << index))).toByte)
      }
    }

    def toList(vect: Vect): List[vect.Item] = {
      val length = vect.length

      def iterator(i: Int, acc: List[vect.Item]): List[vect.Item] = i match {
        case i if i < length => iterator(i + 1, vect.get(i) :: acc)
        case _ => acc.reverse
      }

      iterator(0, List())
    }

    implicit def bool2long(b: Boolean): Long = if (b) 1 else 0

    implicit def int2bool(i: Int): Boolean = if (i == 0) false else true

    implicit def long2bool(i: Long): Boolean = if (i == 0) false else true

    println(BoolVect64(1).get(0))
    println(BoolVect64(1).get(1))
    println(toList(BoolVect8(1)))

    println(BoolVect8(0).set(0, true))

  }

}
