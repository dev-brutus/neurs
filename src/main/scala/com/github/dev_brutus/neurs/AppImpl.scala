package com.github.dev_brutus.neurs

import com.github.dev_brutus.neurs.network.{Base26, Perceptron}

object AppImpl extends App {
  private val perceptron: Perceptron = new Perceptron(Seq(2, 5, 3))

  println(perceptron)

  println(perceptron.changeWeights(Map( ('a1,'b2) -> 0.8  )))

}