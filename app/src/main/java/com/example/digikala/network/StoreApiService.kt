package com.example.digikala.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StoreApiService {
    @GET("home")
    suspend fun getHomePageData(
        @Header("one-api-token") token: String
    ): Response<ResponseBody>

    @GET("product/")
    suspend fun getProductContent(
        @Query("id") id: Long
    ): Response<ResponseBody>
}