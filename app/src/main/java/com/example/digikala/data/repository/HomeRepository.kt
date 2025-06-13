package com.example.digikala.data.repository

import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeRepository {
    @GET("home")
    fun getHomePageData(
    ): Flow<HomePageData>
}