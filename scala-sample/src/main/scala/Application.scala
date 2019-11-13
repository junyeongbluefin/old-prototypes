object Application extends App {

  def mod(modulo: Int)(x: Int) : Boolean = x % modulo == 0
  def even = mod(2)(_)
  def times(times: Int)(x: Int): Int = x * times
  def triple = times(3)(_)
  def double = times(2)(_)

  println("Hello Scala!")
  List(1, 2, 3).foreach(println)
  (1 to 50) map double map triple filter even foreach println

  1.to(20)
    .map(double)
    .map(triple)
    .filter(even)
    .foreach(println)
}