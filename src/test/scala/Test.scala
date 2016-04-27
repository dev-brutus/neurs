/**
  *
  */
object Test extends App {
  var f : Seq[Int] = List(1,2,3,4,5,6)

  println(f.grouped(5).toList)

}
