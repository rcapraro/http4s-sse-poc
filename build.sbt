import Dependencies._

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "http4s-sse-poc",
    libraryDependencies ++= TapirDependencies ++
      RedocDependencies ++
      Http4sDependencies ++
      CatsEffectDependencies ++
      FS2Dependencies ++
      LogDependencies
  )
