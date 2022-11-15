import sbt._

object Dependencies {

  lazy val Tapir     = "com.softwaremill.sttp.tapir"
  lazy val Http4s    = "org.http4s"
  lazy val TypeLevel = "org.typelevel"

  lazy val TapirVersion      = "1.2.1"
  lazy val Http4sVersion     = "0.23.16"
  lazy val CatsEffectVersion = "3.4.0"
  lazy val Fs2Version = "3.3.0"

  val TapirDependencies: Seq[ModuleID] = Seq(
    Tapir %% "tapir-core"       % TapirVersion,
    Tapir %% "tapir-json-circe" % TapirVersion
  )

  val RedocDependencies: Seq[ModuleID] = Seq(
    Tapir %% "tapir-http4s-server" % TapirVersion,
    Tapir %% "tapir-redoc-bundle"  % TapirVersion,
    Tapir %% "tapir-redoc"         % TapirVersion
  )

  val Http4sDependencies: Seq[ModuleID] = Seq(
    Http4s %% "http4s-ember-server" % Http4sVersion,
    Http4s %% "http4s-dsl"          % Http4sVersion
  )

  val CatsEffectDependencies: Seq[ModuleID] = Seq(
    TypeLevel %% "cats-effect" % CatsEffectVersion
  )

  val FS2Dependencies: Seq[ModuleID] = Seq(
    "co.fs2" %% "fs2-core" % Fs2Version,
    "co.fs2" %% "fs2-io" % Fs2Version
  )

  val LogDependencies: Seq[ModuleID] = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.11",
    "org.typelevel" %% "log4cats-slf4j"  % "2.4.0"
  )

}
