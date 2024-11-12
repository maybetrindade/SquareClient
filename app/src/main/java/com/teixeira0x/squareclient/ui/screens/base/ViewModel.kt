package com.teixeira0x.squareclient.ui.screens.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

  private var _isLoading by mutableStateOf(false)
  private var _error by mutableStateOf<String?>(null)

  val isLoading: Boolean
    get() = _isLoading

  val error: String?
    get() = _error

  protected fun <T> call(
    apiCall: suspend () -> Result<T>,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
  ) =
    viewModelScope.launch {
      setError(null)
      setLoading(true)

      val result = apiCall()

      setLoading(false)

      result.getOrNull()?.let { onSuccess?.invoke(it) }
      result.exceptionOrNull()?.let {
        setError(it.message)
        onError?.invoke(it)
      }
    }

  protected fun setLoading(loading: Boolean) {
    _isLoading = loading
  }

  protected fun setError(error: String?) {
    _error = error
  }
}
