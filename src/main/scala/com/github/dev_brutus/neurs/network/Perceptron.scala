package com.github.dev_brutus.neurs.network

import scala.collection.immutable.IndexedSeq

/**
  *
  */
class Perceptron(val neurons: Iterable[Symbol], val weights: Map[(Symbol, Symbol), Double], val outputNeurons: Iterable[Symbol]) {

  val toFrom: Map[Symbol, Iterable[(Symbol, Symbol)]] = weights.keys.groupBy(_._2)

  def apply(initValues: Map[Symbol, Double]) = {
    val networkValues = neurons.foldLeft(initValues) { (acc, neuron) =>
      acc.contains(neuron) match {
        case true => acc
        case false =>
          val neuronValue: Double = toFrom(neuron).view.map(synapse => weights(synapse) * acc(synapse._1)).sum
          acc + (neuron -> neuronValue)
      }
    }

    outputNeurons.view.map(neuron => neuron -> networkValues(neuron)).toMap
  }

  def changeWeights(newWeights: Map[(Symbol, Symbol), Double]) =
    new Perceptron(neurons, weights ++ newWeights, outputNeurons)

  override def toString = s"Perceptron(weights=$weights)"
}

object Perceptron {

  def apply(layers: Iterable[Int]): Perceptron = {
    val neuronsByLayer = layers.view.zipWithIndex.map { layer =>
      (0 until layer._1).map(l => Symbol(s"${Base26(layer._2)}$l"))
    }.toList
    apply(neuronsByLayer, Map.empty)
  }

  def apply(neuronsPerLayer: Seq[IndexedSeq[Symbol]], injectedWeights: Map[(Symbol, Symbol), Double]): Perceptron = {

    val weights: Map[(Symbol, Symbol), Double] = {
      neuronsPerLayer.sliding(2).flatMap { pair =>
        val l0: IndexedSeq[Symbol] = pair(0)
        val l1: IndexedSeq[Symbol] = pair(1)
        l0.flatMap(l => l1.map((l, _)))
      }.map { synapse =>
        synapse -> injectedWeights.getOrElse(synapse, 0.5)
      }.toMap
    }

    val lastLayerNeurons: Seq[Symbol] = neuronsPerLayer.last

    val neuronsByOrder: Iterable[Symbol] = neuronsPerLayer.flatten

    new Perceptron(neuronsByOrder, weights, lastLayerNeurons)
  }
}