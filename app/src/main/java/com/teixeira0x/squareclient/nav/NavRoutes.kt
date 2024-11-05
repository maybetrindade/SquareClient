package com.teixeira0x.squareclient.nav

import kotlinx.serialization.Serializable

@Serializable object TokenScreenRoute

@Serializable data class AccountDetailScreenRoute(val token: String)
