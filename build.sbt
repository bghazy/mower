name := "mower"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
libraryDependencies += "com.lihaoyi" %% "fastparse" % "0.4.3"

val monocleVersion = "1.4.0"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
)