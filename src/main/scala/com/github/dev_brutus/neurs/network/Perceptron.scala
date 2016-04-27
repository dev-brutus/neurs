package com.github.dev_brutus.neurs.network

import scala.collection.immutable.IndexedSeq

/**
  *
  */
class Perceptron(val layers: Seq[Int]) {

  val neuronsPerLayer = layers.view.zipWithIndex.map { layer =>
    (0 until layer._1).map(l => Symbol(s"${layer._2}_$l"))
  }.toList

  val lastLayerNeurons = neuronsPerLayer.last

  val neuronsByOrder = neuronsPerLayer.flatten

  val weights: Map[(Symbol, Symbol), Double] = {
    neuronsPerLayer.sliding(2).flatMap { pair =>
      val l0: IndexedSeq[Symbol] = pair(0)
      val l1: IndexedSeq[Symbol] = pair(1)
      l0.flatMap(l => l1.map((l, _)))
    }.map((_, 0.5)).toMap
  }

  val toFrom = weights.keys.groupBy(_._2)

  def apply(initValues: Map[Symbol, Double]) ={
    val networkValues = neuronsByOrder.foldLeft(initValues) { (acc, neuron) =>
      val neuronValue : Double = toFrom(neuron).view.map(synapse => weights(synapse) * acc(synapse._1)).sum
      acc + (neuron -> neuronValue)
    }

    lastLayerNeurons.view.map(neuron => neuron -> networkValues(neuron)).toMap
  }

  override def toString = s"Perceptron(weights=$weights)"
}
