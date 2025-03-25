package com.example.digikala.data.repository

import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeRepository {
    @GET("home")
    suspend fun getHomePageData(
        @Header("one-api-token") token: String
    ): Flow<HomePageData>
}