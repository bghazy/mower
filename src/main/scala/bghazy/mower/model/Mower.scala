package bghazy.mower.model

import bghazy.mower.model.Instruction.{Forward, TurnLeft, TurnRight}
import bghazy.mower.model.Direction.{East, North, South, West}
import bghazy.mower.model.Mower._position
import bghazy.mower.model.Position.{_x, _y}
import monocle.Lens

case class Mower(position: Position, direction: Direction) {

  private[model] def forward(step: Int = 1): Mower = direction match {
    case North => _position.set(_y.modify(y => y + step)(position))(this)
    case South => _position.set(_y.modify(y => y - step)(position))(this)
    case East => _position.set(_x.modify(x => x + step)(position))(this)
    case West => _position.set(_x.modify(x => x - step)(position))(this)
  }

  def execute(instruction: Instruction): Mower = instruction match {
    case Forward => this.forward()
    case TurnLeft => this.copy(direction = direction.left)
    case TurnRight => this.copy(direction = direction.right)
  }

}

object Mower {

  val _position: Lens[Mower, Position] = Lens[Mower, Position](_.position)(p => m => m.copy(position = p))

}