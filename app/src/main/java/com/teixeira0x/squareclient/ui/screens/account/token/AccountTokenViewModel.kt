package com.teixeira0x.squareclient.ui.screens.account.token

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.teixeira0x.squareclient.domain.model.Account
import com.teixeira0x.squareclient.domain.usecase.account.FetchAccountUseCase
import com.teixeira0x.squareclient.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** @author Felipe Teixeira */
@HiltViewModel
class AccountTokenViewModel
@Inject
constructor(private val fetchAccountUseCase: FetchAccountUseCase) :
  BaseViewModel() {

  private var _state by mutableStateOf(UiState())

  val state: UiState
    get() = _state

  fun updateToken(token: String) {
    _state = _state.copy(token = token)
  }

  fun fetchAccount(
    token: String,
    onSuccess: (Account) -> Unit,
    onError: (Throwable) -> Unit,
  ) {
    call(
      apiCall = { fetchAccountUseCase(token) },
      onSuccess = onSuccess,
      onError = onError,
    )
  }

  data class UiState(val token: String = "")
}
