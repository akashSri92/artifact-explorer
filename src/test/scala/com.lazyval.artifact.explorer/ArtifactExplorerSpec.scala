package com.lazyval.artifact.explorer

import org.scalatest.{FlatSpec, Matchers}

class ArtifactExplorerSpec extends FlatSpec with Matchers {

  "ArtifactExplorer's getVersion" should "provide me version" in {
    assert(!ArtifactExplorer.getDetails(Some("org.typelevel"), Some("cats-effect_2.12"), Some("1.0.0"), None).isEmpty)
  }

  "ArtifactExplorer's getArtifacts" should "provide me artifacts" in {
    assert(!ArtifactExplorer.getDetails(Some("org.typelevel"), Some("cats-effect_2.12"), None).isEmpty)
  }

  "ArtifactExplorer" should "provide me version" in {
    assert(!ArtifactExplorer.getDetails(Some("org.typelevel"), None).isEmpty)
  }

}
