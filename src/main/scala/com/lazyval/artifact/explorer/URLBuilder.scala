package com.lazyval.artifact.explorer

import com.lazyval.artifact.explorer.entities.{Json, ResultFormat, Xml}
import com.softwaremill.sttp.Uri
import java.net.URI

case class URLBuilder(groupId: Option[String], artifactId: Option[String], version: Option[String], resultFormat: Option[ResultFormat]){
  val MAVEN_URL = "https://search.maven.org/solrsearch/select"
  val AND = " AND "
  val VERSION_PARAM = "gav"
  val searchUri = Uri(URI.create(MAVEN_URL))

  def getUrl(): Uri = {
    (groupId, artifactId, version) match {
      case (Some(group), Some(artifact), Some(version)) =>
        searchUri
        .params(
          ("q", getGroupParam(group)+AND+getArtifactParam(artifact)+AND+getVersionParam(version)),
          ("wt", getResultFormatParam(resultFormat)))

      case (Some(group), Some(artifact), None) =>
        searchUri
        .params(
          ("q", getGroupParam(group)+AND+getArtifactParam(artifact)),
          ("core", VERSION_PARAM),
          ("wt", getResultFormatParam(resultFormat))
        )

      case (Some(group), None, None) =>
        searchUri
        .params(
          ("q", getGroupParam(group)),
          ("wt", getResultFormatParam(resultFormat))
        )

      case _ => searchUri
    }
  }

  private def getGroupParam(group: String) = "g:\""+group+"\""

  private def getArtifactParam(artifact: String) = "a:\""+artifact+"\""
  private def getVersionParam(version: String) = "v:\""+version+"\""
  private def getResultFormatParam(resultFormat: Option[ResultFormat]) = resultFormat.map(_ match {
    case Json => "json"
    case Xml => "xml"
  }).getOrElse("json")
}
