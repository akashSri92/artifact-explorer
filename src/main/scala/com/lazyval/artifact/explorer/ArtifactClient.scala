package com.lazyval.artifact.explorer

import com.softwaremill.sttp._
import org.json4s.{JValue}
import org.json4s.native.JsonMethods

object ArtifactClient {
  def getJsonResponse(uri: Uri): JValue = {
    implicit val backend = HttpURLConnectionBackend()

    val request = sttp.get(uri)
    val stringResponse = request.send().unsafeBody
    val jsonResponse = JsonMethods.parse(stringResponse)

    jsonResponse \ "response" \ "docs"
  }
}
