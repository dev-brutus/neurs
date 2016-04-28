package com.github.dev_brutus.neurs.network

import scala.collection.immutable.IndexedSeq

/**
  *
  */
class Perceptron(val neuronsPerLayer: List[IndexedSeq[Symbol]], injectedWeights: Map[(Symbol, Symbol), Double] = Map.empty) {

  def this(layers: Iterable[Int]) {
    this(layers.view.zipWithIndex.map { layer =>
      (0 until layer._1).map(l => Symbol(s"${Base26(layer._2)}$l"))
    }.toList)
  }

  val weights: Map[(Symbol, Symbol), Double] = {
    neuronsPerLayer.sliding(2).flatMap { pair =>
      val l0: IndexedSeq[Symbol] = pair(0)
      val l1: IndexedSeq[Symbol] = pair(1)
      l0.flatMap(l => l1.map((l, _)))
    }.map { synapse =>
      synapse -> injectedWeights.getOrElse(synapse, 0.5)
    }.toMap
  }

  val lastLayerNeurons = neuronsPerLayer.last

  val neuronsByOrder = neuronsPerLayer.flatten

  val toFrom = weights.keys.groupBy(_._2)

  def apply(initValues: Map[Symbol, Double]) = {
    val networkValues = neuronsByOrder.foldLeft(initValues) { (acc, neuron) =>
      val neuronValue: Double = toFrom(neuron).view.map(synapse => weights(synapse) * acc(synapse._1)).sum
      acc + (neuron -> neuronValue)
    }

    lastLayerNeurons.view.map(neuron => neuron -> networkValues(neuron)).toMap
  }

  def changeWeights(newWeights: Map[(Symbol, Symbol), Double]) =
    new Perceptron(neuronsPerLayer, weights ++ newWeights)

  override def toString = s"Perceptron(weights=$weights)"
}
