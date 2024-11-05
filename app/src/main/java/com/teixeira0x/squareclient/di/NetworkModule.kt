package com.teixeira0x.squareclient.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teixeira0x.squareclient.BuildConfig
import com.teixeira0x.squareclient.data.service.AccountService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides @Named("BaseUrl") fun providesBaseUrl() = BuildConfig.BASE_URL

  @Provides fun providesGson() = GsonBuilder().create()

  @Provides
  fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(20, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .writeTimeout(20, TimeUnit.SECONDS)
      .build()
  }

  @Provides
  fun providesRetrofit(
    @Named("BaseUrl") baseUrl: String,
    client: OkHttpClient,
    gson: Gson,
  ): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(client)
      .baseUrl(baseUrl)
      .build()
  }

  @Provides
  fun providesAccountService(retrofit: Retrofit): AccountService =
    retrofit.create(AccountService::class.java)
}
