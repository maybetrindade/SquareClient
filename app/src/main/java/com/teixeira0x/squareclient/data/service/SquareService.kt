package com.teixeira0x.squareclient.data.service

import com.teixeira0x.squareclient.data.model.response.account.ResponsePayload
import com.teixeira0x.squareclient.data.model.response.service.ServiceStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * API service.
 *
 * @author Felipe Teixeira
 */
interface SquareService {

  @GET("service/status")
  suspend fun fetchServiceStatus(): Response<ServiceStatusResponse>

  @GET("users/me")
  suspend fun fetchAccount(
    @Header("Authorization") authorization: String
  ): Response<ResponsePayload>
}
