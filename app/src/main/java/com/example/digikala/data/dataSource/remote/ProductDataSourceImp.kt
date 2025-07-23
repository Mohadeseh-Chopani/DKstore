package com.example.digikala.data.dataSource.remote

import com.example.digikala.data.models.product.AttributeInformationData
import com.example.digikala.data.models.product.ProductPageData
import com.example.digikala.network.StoreApiService
import com.example.digikala.utils.ProcessJson
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductDataSourceImp(val apiService: StoreApiService): ProductDataSource {
    override suspend fun getProductContent(id: Long): Flow<ProductPageData> = flow{
        val response = apiService.getProductContent(id)

        if (!response.isSuccessful) {
            throw Exception("Server returned error: ${response.code()}")
        }

        val jsonString = response.body()?.string() ?: throw Exception("Empty response body")
        val transformedJsonString = ProcessJson.transformJson(jsonString)
        val productData = Gson().fromJson(transformedJsonString, ProductPageData::class.java)

        emit(productData)
    }.flowOn(Dispatchers.IO)


    override suspend fun getAttributeData(id: Long): Flow<AttributeInformationData> = flow{
        val response = apiService.getAttributeData(id)

        if (!response.isSuccessful) {
            throw Exception("Server returned error: ${response.code()}")
        }
        val jsonString = response.body()?.string() ?: throw Exception("Empty response body")
        val transformedJsonString = ProcessJson.transformJson(jsonString)
        val productData = Gson().fromJson(transformedJsonString, AttributeInformationData::class.java)

        emit(productData)
    }.flowOn(Dispatchers.IO)
}