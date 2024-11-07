package com.teixeira0x.squareclient.data.validator.account

/**
 * Class with useful functions to validate a token from the Square Cloud API. 
 *
 * @author Felipe Teixeira
 */
object TokenValidator {

  /** Maximum length of an API token [this value may be wrong]. */
  const val MAX_TOKEN_LENGTH = 84

  /**
   * Checks if the string is a valid token.
   *
   * @return If it is valid [true] if it is invalid [false].
   */
  fun String.isValidToken(): Boolean {
    val token = this.trim()
    return token.isNotEmpty() && token.length == MAX_TOKEN_LENGTH
  }
}
