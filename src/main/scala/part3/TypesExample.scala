package part3

import Math._

object TypesExample extends App{
  def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
    1/(sigma * sqrt(2 * PI)) * exp(-pow((x - mu), 2)/(2 * pow(sigma, 2)))
  }

  println(normalDistribution(1,1,1))
}
