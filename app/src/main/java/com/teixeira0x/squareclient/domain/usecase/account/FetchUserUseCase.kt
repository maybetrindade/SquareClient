package com.teixeira0x.squareclient.domain.usecase.account

import com.teixeira0x.squareclient.domain.model.account.Account
import com.teixeira0x.squareclient.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Use case for getting an account from the account repository.
 *
 * @author Felipe Teixeira
 */
class FetchAccountUseCase
@Inject
constructor(private val repository: AccountRepository) {

  suspend operator fun invoke(token: String): Result<Account> {
    return repository.fetchAccount(token)
  }
}
