package com.example.digikala.utils

import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser

object ProcessJson {
    internal fun transformJson(jsonString: String): String {
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