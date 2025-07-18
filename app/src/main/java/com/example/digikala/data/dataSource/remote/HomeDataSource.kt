package com.example.digikala.data.dataSource.remote

import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface HomePageDataSource {
    @GET("home")
    fun getHomePageData(
    ): Flow<HomePageData>
}