package com.lazyval.artifact.explorer

import com.lazyval.artifact.explorer.entities._
import org.json4s
import org.json4s.JsonAST
import org.json4s.JsonAST.JValue

//TODO: handle Repository
//TODO: handle Version classification (snapshot or milestone)
object ArtifactExplorer {

  implicit val formats = json4s.DefaultFormats

  //TODO: Handle XML formats ?
  def getVersion(
      groupId: Option[String],
      artifactId: Option[String],
      version: Option[String],
      resultFormat: Option[ResultFormat] = None,
      scalaVersion: Option[String] = None): Seq[Version] = {
    val generatedUri = URLBuilder(groupId, artifactId, version, resultFormat, scalaVersion).getUrl()
    val jsonResponse = ArtifactClient.getJsonResponse(generatedUri)

    (groupId.isDefined, artifactId.isDefined, version.isDefined) match {
      case (true, true, true) => getSearchArtifacts(jsonResponse).headOption.map(EntityConvertor.getVersion).toSeq
      case _ => Seq.empty
    }
  }

  def getArtifacts(
      groupId: Option[String],
      artifactId: Option[String],
      resultFormat: Option[ResultFormat] = None,
      scalaVersion: Option[String] = None): Seq[Artifact] = {
    val generatedUri = URLBuilder(groupId, artifactId, None, resultFormat, scalaVersion).getUrl()
    val jsonResponse = ArtifactClient.getJsonResponse(generatedUri)

    (groupId.isDefined, artifactId.isDefined) match {
      case (true, true) => EntityConvertor.getArtifact(getSearchArtifacts(jsonResponse)).toSeq
      case _ => Seq.empty
    }
  }

  def getArtifactForAllVersions(groupId: Option[String], artifactId: Option[String]) = {
    val generatedUri = URLBuilder(groupId, artifactId, getAllVersions = true).getUrl()
    val jsonResponse = ArtifactClient.getJsonResponse(generatedUri)

    (groupId.isDefined, artifactId.isDefined) match {
      case (true, true) => EntityConvertor.getArtifact(getSearchArtifacts(jsonResponse)).toSeq
      case _ => Seq.empty
    }
  }

  def getGroupDetails(groupId: Option[String], resultFormat: Option[ResultFormat] = None): Seq[Group] = {
    val generatedUri = URLBuilder(groupId, None, None, resultFormat).getUrl()
    val jsonResponse = ArtifactClient.getJsonResponse(generatedUri)

    if (groupId.isEmpty)
      Seq.empty
    else {
      val mavenArtifacts = getMavenArtifacts(jsonResponse)
      EntityConvertor.getGroup(mavenArtifacts).toSeq
    }
  }

  private def getSearchArtifacts(json: JValue) = json match {
    case JsonAST.JArray(arr) => arr.map(_.extract[SearchArtifact])
    case _ => List.empty
  }

  private def getMavenArtifacts(json: JValue) = json match {
    case JsonAST.JArray(arr) => arr.map(_.extract[MavenArtifact])
    case _ => List.empty
  }
}
