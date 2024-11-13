package com.teixeira0x.squareclient.data.repository

import com.teixeira0x.squareclient.data.mapper.account.AccountMapper.toAccount
import com.teixeira0x.squareclient.data.model.RequestException
import com.teixeira0x.squareclient.data.service.SquareService
import com.teixeira0x.squareclient.domain.model.account.Account
import com.teixeira0x.squareclient.domain.repository.AccountRepository
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * Implementation of the account repository.
 *
 * @author Felipe Teixeira
 */
class AccountRepositoryImpl
@Inject
constructor(private val service: SquareService) : AccountRepository {

  override suspend fun fetchAccount(token: String): Result<Account> {
    return try {
      val response = service.fetchAccount(token)

      if (response.isSuccessful && response.body()?.status == "success") {
        val account = response.body()?.response?.toAccount()
        Result.success(account!!)
      } else {
        Result.failure(
          RequestException(
            message = "Unable to fetch account",
            code = response.code(),
          )
        )
      }
    } catch (th: Throwable) {
      Result.failure(
        RequestException(
          message = th.message ?: "",
          code = HttpURLConnection.HTTP_INTERNAL_ERROR,
        )
      )
    }
  }
}
