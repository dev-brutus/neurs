package com.github.dev_brutus.neurs

import com.github.dev_brutus.neurs.network.Perceptron

object AppImpl extends App {
  println(new Perceptron(Seq(2, 5, 3)))
}