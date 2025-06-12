package com.example.digikala.data.dataSource

import com.example.digikala.data.models.search.SearchData
import com.example.digikala.network.StoreApiService
import com.example.digikala.utils.ProcessJson
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchDataSourceImp(val apiService: StoreApiService): SearchDataSource {
    override suspend fun getSearchData(query: String): Flow<SearchData> = flow{
        val response = apiService.getSearchData(query)

        if (!response.isSuccessful) {
            throw Exception("Server returned error: ${response.code()}")
        }

        val jsonString = response.body()?.string() ?: throw Exception("Empty response body")
        val transformedJsonString = ProcessJson.transformJson(jsonString)
        val productData = Gson().fromJson(transformedJsonString, SearchData::class.java)

        emit(productData)
    }.flowOn(Dispatchers.IO)
}