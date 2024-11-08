package com.teixeira0x.squareclient.ui.screens.account.token

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.teixeira0x.squareclient.domain.model.Account
import com.teixeira0x.squareclient.domain.usecase.account.FetchAccountUseCase
import com.teixeira0x.squareclient.ui.components.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** @author Felipe Teixeira */
@HiltViewModel
class AccountTokenViewModel
@Inject
constructor(private val fetchAccountUseCase: FetchAccountUseCase) :
  BaseViewModel() {

  var uiState by mutableStateOf(UiState())

  fun updateToken(token: String) {
    uiState = uiState.copy(token = token)
  }

  fun updateErrorMessage(errorMessage: String?) {
    uiState = uiState.copy(errorMessage = errorMessage)
  }

  fun fetchAccount(
    token: String,
    onSuccess: (Account) -> Unit,
    onError: (Throwable) -> Unit,
  ) {
    uiState = uiState.copy(loading = true)
    call(
      apiCall = { fetchAccountUseCase(token) },
      onSuccess = {
        uiState = uiState.copy(loading = false)
        onSuccess(it)
      },
      onError = {
        uiState = uiState.copy(loading = false)
        onError(it)
      },
    )
  }

  data class UiState(
    val token: String = "",
    val errorMessage: String? = null,
    val loading: Boolean = false,
  )
}
