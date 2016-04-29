/**
  *
  */
object Test extends App {
//  var f : Seq[Int] = List(1,2,3,4,5,6)
//
//  println(f.grouped(5).toList)

  private val list1: List[Double] = List(0.0, 1.125, 0.2, 0.6, 1)
  private val list2: List[Double] = List(0.0, 1.125, 1.6, 0.2)

  def s (l:List[Double]) {
    println(l.map(v=>f"$v%18.16f").mkString(", "))
    println(f"${l.sum}%18.16f")
  }

  s(list1)
  s(list2)
}
