package com.lazyval.artifact.explorer

import com.lazyval.artifact.explorer.entities._

object EntityConvertor {

  def getVersion(artifact: SearchArtifact) = Version(artifact.v)

  def getArtifact(artifacts: Seq[SearchArtifact]) = {
    val versions = artifacts.map(getVersion)
    if (versions.isEmpty)
      None
    else
      Some(Artifact(artifacts.head.a, versions, versions.head))
  }

  def getGroup(mavenArtifacts: Seq[MavenArtifact]) = {
    val allArtifacts =
      mavenArtifacts
        .map(artifact => ArtifactExplorer.getArtifactForAllVersions(Some(artifact.g), Some(artifact.a)))
        .flatten
    if (allArtifacts.isEmpty)
      None
    else
      Some(Group(mavenArtifacts.head.g, allArtifacts, None))
  }
}
