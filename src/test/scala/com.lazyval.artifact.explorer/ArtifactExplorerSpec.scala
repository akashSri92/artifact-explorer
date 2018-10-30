package com.lazyval.artifact.explorer

import org.scalatest.{FlatSpec, Matchers}

class ArtifactExplorerSpec extends FlatSpec with Matchers {

  "ArtifactExplorer's getVersion" should "provide me version" in {
    assert(ArtifactExplorer.getVersion(Some("org.typelevel"), Some("cats-effect"), Some("1.0.0")).nonEmpty)
  }

  "ArtifactExplorer's getArtifacts" should "provide me artifacts with default scala version" in {
    assert(ArtifactExplorer.getArtifacts(Some("org.typelevel"), Some("cats-effect")).nonEmpty)
  }

  "ArtifactExplorer's getArtifacts" should "provide me artifacts with scala version 2.11" in {
    assert(ArtifactExplorer.getArtifacts(Some("org.typelevel"), Some("cats-effect"), scalaVersion = Some("2.11")).nonEmpty)
  }

  "ArtifactExplorer's getArtifacts" should "provide me artifacts with major scala version 2.11 for provided scala version 2.11.3" in {
    assert(ArtifactExplorer.getArtifacts(Some("org.typelevel"), Some("cats-effect"), scalaVersion = Some("2.11.3")).nonEmpty)
  }

  "ArtifactExplorer's getArtifacts" should "provide me empty list of artifacts with scala version 2.13" in {
    assert(ArtifactExplorer.getArtifacts(Some("org.typelevel"), Some("cats-effect"), scalaVersion = Some("2.13")).isEmpty)
  }

  "ArtifactExplorer's getGroupDetails" should "provide me all artifacts with versions" in {
    assert(ArtifactExplorer.getGroupDetails(Some("org.typelevel")).nonEmpty)
  }

}
