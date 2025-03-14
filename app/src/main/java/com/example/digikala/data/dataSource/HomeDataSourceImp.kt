package com.example.digikala.data.dataSource

import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.network.StoreApiService
import kotlinx.coroutines.flow.Flow

class HomeDataSourceImp(val apiService: StoreApiService): HomeDataSource {
    override fun getHomeData(token: String): Flow<HomePageData> {
        TODO("Not yet implemented")
    }
}