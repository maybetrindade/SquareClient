package com.teixeira0x.squareclient.domain.model

/** @author Felipe Teixeira */
data class Application(
  val id: String,
  val name: String,
  val desc: String,
  val ram: Long,
  val lang: String,
  val domain: String,
  val custom: String,
  val cluster: String,
)
