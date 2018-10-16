package com.lazyval.artifact.explorer

import com.lazyval.artifact.explorer.entities.{Artifact, Group, Version}

object Test extends App {
  val t1: Seq[Version] = ArtifactExplorer.getDetails(Some("org.typelevel"), Some("cats-effect_2.12"), Some("1.0.0"), None)
  val t2: Seq[Artifact] = ArtifactExplorer.getDetails(Some("org.typelevel"), Some("cats-effect_2.12"), None)
  val t3: Seq[Group] = ArtifactExplorer.getDetails(Some("org.typelevel"), None)

  println("Test1:  " + t1)
  println("Test2:  " + t2)
  println("Test3:  " + t3)
}
