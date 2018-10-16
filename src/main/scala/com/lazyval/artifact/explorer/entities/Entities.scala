package com.lazyval.artifact.explorer.entities

sealed trait Entities

case class Repository(name: String, url: String) extends Entities

case class Version(id: String, isSnapshot: Boolean = false, isMilestone: Boolean = false) extends Entities

case class Artifact(id: String, versions: Seq[Version], latest: Version) extends Entities

case class Group(id: String, artifacts: Seq[Artifact], repo: Option[Repository]) extends Entities
