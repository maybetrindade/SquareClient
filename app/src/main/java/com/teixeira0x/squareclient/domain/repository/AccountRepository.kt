package com.teixeira0x.squareclient.domain.repository

import com.teixeira0x.squareclient.domain.model.account.Account

/**
 * Account repository.
 *
 * @author Felipe Teixeira
 */
interface AccountRepository {

  /**
   * Fetch the account from the provided token.
   *
   * @param token The token of the account to be fetched.
   * @return The result of the account fetch.
   */
  suspend fun fetchAccount(token: String): Result<Account>
}
