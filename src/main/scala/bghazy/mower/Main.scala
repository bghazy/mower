package bghazy.mower

import fastparse.core.Parsed
import bghazy.mower.parser.Parser

import scala.io.Source.fromInputStream

object Main extends App {

  val file = "/mowers.txt"

  Option(getClass.getResourceAsStream(file)) match {
    case None =>
      println(s"Error while reading the file $file")
      System.exit(1)

    case Some(stream) =>
      val source = fromInputStream(stream)
      val lines = try source.getLines.mkString("\n") finally source.close()

      Parser.description.parse(lines) match {
        case Parsed.Success((lawn, mowers), _) =>
          println("initial positions and commands:" + mowers)
          println("final positions:" +lawn.move(mowers))
          System.exit(0)
        case Parsed.Failure(p, i, _) =>
          println(s"Error while parsing : expected $p at index $i")
          System.exit(2)
      }
  }

}