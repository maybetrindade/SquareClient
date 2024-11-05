package com.teixeira0x.squareclient.domain.repository

import com.teixeira0x.squareclient.domain.model.Account

/** @author Felipe Teixeira */
interface AccountRepository {

  suspend fun fetchAccount(token: String): Result<Account>
}
