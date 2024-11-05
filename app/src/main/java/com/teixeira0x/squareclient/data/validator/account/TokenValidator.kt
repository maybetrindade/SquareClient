package com.teixeira0x.squareclient.data.validator.account

/** @author Felipe Teixeira */
object TokenValidator {

  const val MAX_TOKEN_LENGTH = 84

  fun String.isValidToken(): Boolean {
    val token = this.trim()
    return token.isNotEmpty() && token.length == MAX_TOKEN_LENGTH
  }
}
