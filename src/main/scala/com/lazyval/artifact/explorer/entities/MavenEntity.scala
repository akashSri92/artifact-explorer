package com.lazyval.artifact.explorer.entities

sealed trait MavenEntity

case class SearchArtifact(id: String,
                          g: String,
                          a: String,
                          v: String,
                          p: String,
                          timestamp: String,
                          ec: Seq[String],
                          tags: Seq[String]) extends MavenEntity

case class MavenArtifact(id: String,
                         g: String,
                         a: String,
                         latestVersion: String,
                         repositoryId: String,
                         p: String,
                         timestamp: String,
                         versionCount: Int,
                         text: Seq[String],
                         ec: Seq[String]) extends MavenEntity
