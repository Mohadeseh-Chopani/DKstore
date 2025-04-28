package com.example.digikala.data.dataSource

import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header

interface HomePageDataSource {
    @GET("home")
    suspend fun getHomePageData(
        @Header("one-api-token") token: String
    ): Flow<HomePageData>
}