package com.teixeira0x.squareclient.data.model

/** @author Felipe Teixeira */
class RequestException(val code: Int, message: String) :
  Throwable("$message: $code")
