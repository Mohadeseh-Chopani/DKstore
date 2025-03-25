package com.example.digikala.network

import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow
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
}