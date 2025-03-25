package com.example.digikala.data.dataSource

import android.util.Log
import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.network.StoreApiService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class HomePageDataSourceImp(val apiService: StoreApiService) : HomePageDataSource {
    override suspend fun getHomePageData(token: String): Flow<HomePageData> = flow {
        val response = apiService.getHomePageData(token)

        if (!response.isSuccessful) {
            throw Exception("Server returned error: ${response.code()}")
        }

        val jsonString = response.body()?.string() ?: throw Exception("Empty response body")
        Log.d("HomePageDataSourceImp", "Raw JSON: $jsonString")

        val transformedJsonString = transformJson(jsonString)
        Log.d("HomePageDataSourceImp", "Transformed JSON: $transformedJsonString")

        val HomePageData = Gson().fromJson(transformedJsonString, HomePageData::class.java)
        emit(HomePageData)
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
            Log.e("HomePageDataSourceImp", "Error parsing JSON: ${e.message}")
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
