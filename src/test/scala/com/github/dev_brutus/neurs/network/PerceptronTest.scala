package com.github.dev_brutus.neurs.network

import org.junit.{Assert, Test}

/**
  *
  */
@Test
class PerceptronTest {

  @Test
  def test(): Unit = {
    val perceptron: Perceptron = Perceptron(List(2, 5, 3))
    Assert.assertEquals("Neurons count", 2 + 5 + 3, perceptron.neurons.size)
    val neuronPairs = perceptron.neurons.zip(List('a0, 'a1, 'b0, 'b1, 'b2, 'b3, 'b4, 'c0, 'c1, 'c2))
    Assert.assertTrue("Neuron names", neuronPairs.forall(p => p._1 == p._2))


  }
}
