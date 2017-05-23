package bghazy.mower.parser

import bghazy.mower.model._

object Parser {

  import fastparse.all._

  type MowersAndInstructions = Seq[(Mower, List[Instruction])]

  private val num: P[Int] = P(CharIn('0' to '9').rep(1)).!.map(_.toInt)
  private val breakLine: P[Unit] = P("\n")
  private val space: P[Unit] = P(" ")
  private val coordinates: P[(Int, Int)] = P(num ~ space ~ num)
  private[parser] val instruction: P[Instruction] = P(CharIn("AGD")).!.map(Instruction.iso.get)

  val direction: P[Direction] = P(CharIn("NSEW")).!.map(Direction.iso.get)

  val instructions: P[List[Instruction]] = P(instruction.rep(1)).map(_.toList)

  val lawn: P[Lawn] = coordinates.map(Lawn.iso.get)

  val position: P[Position] = coordinates.map { case (x, y) => Position(x, y) }

  val mower: P[Mower] = P(position ~ space ~ direction).map { case (p, d) => Mower(p, d) }

  private val mowersAndInstructions: P[MowersAndInstructions] = P(mower ~ breakLine ~ instructions).rep(1, sep = breakLine)

  val description: P[(Lawn, MowersAndInstructions)] = P(Start ~ lawn ~ breakLine ~ mowersAndInstructions ~ End)
}