package com.example.digikala.utils

import androidx.room.TypeConverter
import com.example.digikala.data.models.product.Product
import com.google.gson.Gson

class ProductConverter {

    @TypeConverter
    fun fromProduct(product: Product): String {
        return Gson().toJson(product)
    }

    @TypeConverter
    fun toProduct(json: String): Product {
        return Gson().fromJson(json, Product::class.java)
    }
}