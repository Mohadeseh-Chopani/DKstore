package com.example.digikala.data.dataSource

import com.example.digikala.data.models.category.CategoriesData
import com.example.digikala.data.models.product.ProductPageData
import com.example.digikala.network.StoreApiService
import com.example.digikala.utils.ProcessJson
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoriesDataSourceImp(val apiService: StoreApiService): CategoriesDataSource {
    override suspend fun getCategoriesData(): Flow<CategoriesData> = flow{
        val response = apiService.getCategoriesData()

        if (!response.isSuccessful) {
            throw Exception("Server returned error: ${response.code()}")
        }

        val jsonString = response.body()?.string() ?: throw Exception("Empty response body")
        val transformedJsonString = ProcessJson.transformJson(jsonString)
        val productData = Gson().fromJson(transformedJsonString, CategoriesData::class.java)

        emit(productData)
    }.flowOn(Dispatchers.IO)
}