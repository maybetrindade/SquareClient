package com.teixeira0x.squareclient.di

import com.teixeira0x.squareclient.data.repository.AccountRepositoryImpl
import com.teixeira0x.squareclient.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract fun bindsAccountRepository(
    repository: AccountRepositoryImpl
  ): AccountRepository
}
