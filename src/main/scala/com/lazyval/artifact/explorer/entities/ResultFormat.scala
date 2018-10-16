package com.lazyval.artifact.explorer.entities

sealed trait ResultFormat

case object Json extends ResultFormat

case object Xml extends ResultFormat
