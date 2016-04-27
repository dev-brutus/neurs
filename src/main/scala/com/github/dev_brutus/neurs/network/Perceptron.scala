package com.github.dev_brutus.neurs.network

import scala.annotation.tailrec

/**
  *
  */
class Perceptron(val layers: Seq[Int], weights: Seq[Array[Double]]) {

  def this(layers: Seq[Int]) {
    this(layers, layers.sliding(2).map(layersPair => Array.fill(layersPair.product)(0.5)).toVector)
  }

  private val values: Vector[Array[Double]] = layers.map {
    new Array[Double](_)
  }.toVector

  private val vwv: List[(Array[Double], Array[Double], Array[Double])] = {
    values.sliding(2).toList.zip(weights).map { t =>
      (t._1.head, t._2, t._1(1))
    }
  }

  def apply(input: Array[Double]): Array[Double] = {
    val length: Int = input.length
    if (length != layers.head) {
      throw new IllegalArgumentException(s"Length must be $length")
    }

    System.arraycopy(input, 0, values.head, 0, length)

    vwv.foreach { t =>
      val (valuesFrom, weights, valuesTo) = t

      weights.grouped(valuesFrom.length).zipWithIndex.foreach{ u=>
        valuesTo(u._2) = u._1.view.zip(valuesFrom).map({wv => wv._1 * wv._2}).sum
      }

    }

    values.last.clone()
  }

}
