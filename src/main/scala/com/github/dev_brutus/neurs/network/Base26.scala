package com.github.dev_brutus.neurs.network

/**
  *
  */
object Base26 {

  private def incStr(s: String) = {
    val preResult = s.foldRight(1 -> "") { (char, result) =>
      result match {
        case (0, acc) => (0, char + acc)
        case (1, acc) =>
          if (char == 'z') (1, 'a' + acc)
          else (0, (char.toInt + 1).toChar + acc)
      }
    }

    preResult match {
      case (1, res) => 'a' + res
      case (_, res) => res
    }
  }

  private def cache(init: String): Stream[String] = init #:: cache(incStr(init))

  val data: Stream[String] = cache("a")

  def apply(index: Int): String = data(index)
}
