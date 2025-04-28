package com.example.digikala.data.dataSource

import android.util.Log
import com.example.digikala.data.models.product.ProductPageData
import com.example.digikala.network.StoreApiService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
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
        Log.d("HomePageDataSourceImp", "Raw JSON: $jsonString")

        val transformedJsonString = transformJson(jsonString)
        Log.d("HomePageDataSourceImp", "Transformed JSON: $transformedJsonString")

        val productData = Gson().fromJson(transformedJsonString, ProductPageData::class.java)

        emit(productData)
    }.flowOn(Dispatchers.IO)

    private fun transformJson(jsonString: String): String {
        return try {
            val jsonElement = JsonParser.parseString(jsonString)
            if (jsonElement.isJsonObject) {
                val jsonObject = jsonElement.asJsonObject
                processJsonObject(jsonObject)
            }
            jsonElement.toString()
        } catch (e: Exception) {
            Log.e("ProductPageDataSourceImp", "Error parsing JSON: ${e.message}")
            jsonString
        }
    }

    private fun processJsonObject(jsonObject: JsonObject) {
        for ((key, value) in jsonObject.entrySet()) {
            when {
                value.isJsonArray && value.asJsonArray.size() == 0 -> {
                    jsonObject.add(key, JsonObject())
                }
                value.isJsonObject -> {
                    processJsonObject(value.asJsonObject)
                }
                value.isJsonArray -> {
                    value.asJsonArray.forEach { element ->
                        if (element.isJsonObject) {
                            processJsonObject(element.asJsonObject)
                        }
                    }
                }
            }
        }
    }
}