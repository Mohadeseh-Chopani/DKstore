package com.example.digikala.network

import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApiService {
    @GET("home")
    fun getHomeData(
        @Query("token") token: String
    ): Flow<HomePageData>
}