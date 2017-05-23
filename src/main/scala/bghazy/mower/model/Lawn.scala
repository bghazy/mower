package bghazy.mower.model

import bghazy.mower.parser.Parser.MowersAndInstructions
import monocle._

case class Lawn (x: Int, y: Int) {

  def inside(position: Position) =
    position.x >= 0 &&
      position.y >= 0 &&
      position.y <= y &&
      position.x <= x

  def move(mowers: MowersAndInstructions) = for (mowerAndInstructions <- mowers) yield {
    val mower = mowerAndInstructions._1
    val instructions = mowerAndInstructions._2

    instructions.foldLeft(mower) {
      case (m, i) =>
        val next = m.execute(i)
        if (this.inside(next.position)) next else m
    }

  }

}

object Lawn {

  val iso: Iso[(Int, Int), Lawn] = Iso[(Int, Int), Lawn] {
    case (xSize, ySize) => Lawn(xSize, ySize)
  } {
    lawn => (lawn.x, lawn.y)
  }

}