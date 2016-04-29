package com.github.dev_brutus.neurs

import com.github.dev_brutus.neurs.network.{Base26, Perceptron}

object AppImpl extends App {
  private val perceptron = new Perceptron(Seq(2, 5, 3)).changeWeights(Map(
    ('a0,'b0) -> 1.0,
    ('a0,'b1) -> 0.8,
    ('a0,'b2) -> 0.5,
    ('a0,'b3) -> 0.0,
    ('a0,'b4) -> 0.0,
    ('a1,'b4) -> 1.0,
    ('a1,'b3) -> 0.8,
    ('a1,'b2) -> 0.5,
    ('a1,'b1) -> 0.0,
    ('a1,'b0) -> 0.0
  )).changeWeights(Map(
    ('b0,'c1) -> 0,
    ('b0,'c2) -> 0,
    ('b1,'c2) -> 0,
    ('b2,'c0) -> 0,
    ('b2,'c2) -> 0,
    ('b3,'c0) -> 0,
    ('b4,'c0) -> 0,
    ('b4,'c1) -> 0
  ))

  println(perceptron)

  println(perceptron(Map('a0 -> 4, 'a1 -> 0.5)))

}