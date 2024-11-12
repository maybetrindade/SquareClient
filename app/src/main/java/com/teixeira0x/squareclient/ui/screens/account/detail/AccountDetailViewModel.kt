package com.teixeira0x.squareclient.ui.screens.account.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.teixeira0x.squareclient.domain.model.account.Account
import com.teixeira0x.squareclient.domain.usecase.account.FetchAccountUseCase
import com.teixeira0x.squareclient.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** @author Felipe Teixeira */
@HiltViewModel
class AccountDetailViewModel
@Inject
constructor(private val fetchAccountUseCase: FetchAccountUseCase) :
  BaseViewModel() {

  private var _state by mutableStateOf(AccountDetailState())

  val state: AccountDetailState
    get() = _state

  fun updateAccount(account: Account?) {
    this._state = _state.copy(account = account)
  }

  fun fetchAccount(token: String) {
    call(
      apiCall = { fetchAccountUseCase(token) },
      onSuccess = { updateAccount(it) },
    )
  }

  data class AccountDetailState(
    val account: Account? = null,
    val hasNetwork: Boolean = true,
  )
}
