package com.teixeira0x.squareclient.data.model.response.account

/** @author Felipe Teixeira */
data class ResponsePayload(
  val status: String,
  val code: String? = null,
  val response: AccountResponse? = null,
)

data class AccountResponse(
  val user: UserResponse,
  val applications: List<ApplicationResponse>,
)

data class UserResponse(
  val id: String,
  val name: String,
  val email: String,
  val plan: PlanResponse,
)

data class PlanResponse(
  val name: String,
  val memory: MemoryResponse,
  val duration: Long? = null,
)

data class MemoryResponse(val limit: Long, val available: Long, val used: Long)

data class ApplicationResponse(
  val id: String,
  val name: String,
  val desc: String,
  val ram: Long,
  val lang: String,
  val domain: String,
  val custom: String,
  val cluster: String,
)
