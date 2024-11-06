package com.teixeira0x.squareclient.ui.screens.account.detail

import androidx.lifecycle.ViewModel
import com.teixeira0x.squareclient.domain.usecase.account.FetchAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** @author Felipe Teixeira */
@HiltViewModel
class AccountDetailViewModel
@Inject
constructor(private val fetchAccountUseCase: FetchAccountUseCase) :
  ViewModel() {}
