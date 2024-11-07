package com.teixeira0x.squareclient.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

  private var apiState by mutableStateOf(APIState())

  val isLoading: Boolean
    get() = apiState.isLoading

  val error: String?
    get() = apiState.error

  protected fun <T> call(
    apiCall: suspend () -> Result<T>,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
  ) =
    viewModelScope.launch {
      updateLoading(true)

      val result = apiCall()

      updateLoading(false)

      result.getOrNull()?.let { onSuccess?.invoke(it) }
      result.exceptionOrNull()?.let {
        updateError(it.message)
        onError?.invoke(it)
      }
    }

  protected fun updateLoading(loading: Boolean) {
    apiState = apiState.copy(isLoading = loading)
  }

  protected fun updateError(error: String?) {
    apiState = apiState.copy(error = error)
  }

  data class APIState(
    val isLoading: Boolean = false,
    val error: String? = null,
  )
}
