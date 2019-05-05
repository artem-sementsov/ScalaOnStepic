package part4

object Middle extends App{
  def middle[T](data: Iterable[T]) = {
    (data.splitAt(data.size / 2: Int): (Iterable[T], Iterable[T]))._2.head
  }

  require(middle("Scala") == 'a')
  require(middle(Seq(1, 7, 5, 9)) == 5)

  for(i <- Range(1, 10)) {
    println(i)
  }
}
