package com.example.digikala.data.dataSource

import android.util.Log
import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.network.StoreApiService
import com.example.digikala.utils.ProcessJson
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomePageDataSourceImp(val apiService: StoreApiService) : HomePageDataSource {
    override fun getHomePageData(): Flow<HomePageData> = flow {
        val response = apiService.getHomePageData()

        if (!response.isSuccessful) {
            throw Exception("Server returned error: ${response.code()}")
        }

        val jsonString = response.body()?.string() ?: throw Exception("Empty response body")
        Log.d("HomePageDataSourceImp", "Raw JSON: $jsonString")

        val transformedJsonString = ProcessJson.transformJson(jsonString)
        Log.d("HomePageDataSourceImp", "Transformed JSON: $transformedJsonString")

        val homeData = Gson().fromJson(transformedJsonString, HomePageData::class.java)
        emit(homeData)
    }.flowOn(Dispatchers.IO)
}
