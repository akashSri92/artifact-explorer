name := "artifact-explorer"
organization := "com.lazyval"
scalaVersion := "2.12.6"

val sttpVersion = "1.3.6"
val json4sVersion = "3.5.3"
val scalacticVersion = "3.0.5"
val scalatestVersion = "3.0.5"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp" %% "core" % sttpVersion,
  "org.json4s" %% "json4s-native" % json4sVersion,
  "org.scalactic" %% "scalactic" % scalacticVersion,
  "org.scalatest" %% "scalatest" % scalatestVersion % "test"
)