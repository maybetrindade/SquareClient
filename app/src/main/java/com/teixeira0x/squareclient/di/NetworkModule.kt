package com.teixeira0x.squareclient.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teixeira0x.squareclient.BuildConfig
import com.teixeira0x.squareclient.data.service.SquareService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides @Named("BaseUrl") fun providesBaseUrl() = BuildConfig.BASE_URL

  @Provides fun providesGson() = GsonBuilder().create()

  @Provides
  fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
    val cacheSize = (10 * 1024 * 1024).toLong()
    val cache = Cache(context.cacheDir, cacheSize)
    return OkHttpClient.Builder()
      .cache(cache)
      .addInterceptor(
        HttpLoggingInterceptor().apply {
          level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
      )
      .connectTimeout(1, TimeUnit.MINUTES)
      .readTimeout(1, TimeUnit.MINUTES)
      .writeTimeout(1, TimeUnit.MINUTES)
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
  fun providesSquareService(retrofit: Retrofit): SquareService =
    retrofit.create(SquareService::class.java)
}
