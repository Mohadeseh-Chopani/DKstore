package com.example.digikala.data.repository

import com.example.digikala.data.models.category.CategoriesData
import com.example.digikala.data.models.product.AttributeInformationData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesRepository {
    @GET("categories/")
    suspend fun getCategoriesData(): Flow<CategoriesData>
}