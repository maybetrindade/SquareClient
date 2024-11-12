package com.teixeira0x.squareclient.data.repository

import com.teixeira0x.squareclient.data.mapper.account.AccountMapper.toAccount
import com.teixeira0x.squareclient.data.model.RequestException
import com.teixeira0x.squareclient.data.service.AccountService
import com.teixeira0x.squareclient.domain.model.Account
import com.teixeira0x.squareclient.domain.repository.AccountRepository
import java.net.HttpURLConnection
import javax.inject.Inject

/** @author Felipe Teixeira */
class AccountRepositoryImpl
@Inject
constructor(private val service: AccountService) : AccountRepository {

  override suspend fun fetchAccount(token: String): Result<Account> {
    try {
      val response = service.fetchAccount(token).body()
      if (response?.status == "success") {
        val account = response.response!!.toAccount()
        return Result.success(account)
      }
    } catch (th: Throwable) {
      th.printStackTrace()
    }

    return Result.failure(
      RequestException(
        message = "Unable to fetch account",
        code = HttpURLConnection.HTTP_INTERNAL_ERROR,
      )
    )
  }
}
