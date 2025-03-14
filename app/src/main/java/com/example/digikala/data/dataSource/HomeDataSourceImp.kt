package com.example.digikala.data.dataSource

import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.network.StoreApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class HomeDataSourceImp(val apiService: StoreApiService): HomeDataSource {
    override fun getHomeData(token: String): Flow<HomePageData> = flow {
        val response = apiService.getHomeData(token)
        emit(response)
    }.flowOn(Dispatchers.IO)
}