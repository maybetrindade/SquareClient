package com.teixeira0x.squareclient.domain.model.account

/** @author Felipe Teixeira */
data class User(
  val id: String,
  val name: String,
  val email: String,
  val plan: Plan,
)
