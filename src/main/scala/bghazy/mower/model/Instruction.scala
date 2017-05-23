package bghazy.mower.model

import monocle.Iso

sealed trait Instruction

object Instruction {

  case object Forward extends Instruction

  case object TurnLeft extends Instruction

  case object TurnRight extends Instruction

  val iso: Iso[String, Instruction] = Iso[String, Instruction] {
    case "A" => Forward
    case "G" => TurnLeft
    case "D" => TurnRight
  } {
    case Forward => "A"
    case TurnLeft => "G"
    case TurnRight => "D"
  }

}