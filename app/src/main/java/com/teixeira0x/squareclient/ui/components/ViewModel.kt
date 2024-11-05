package com.teixeira0x.squareclient.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

  protected fun <T> call(
    apiCall: suspend () -> Result<T>,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
  ) =
    viewModelScope.launch {
      val result = apiCall()
      result.getOrNull()?.let { onSuccess?.invoke(it) }
      result.exceptionOrNull()?.let { onError?.invoke(it) }
    }
}
