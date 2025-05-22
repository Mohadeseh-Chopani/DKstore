package com.example.digikala.data.dataSource

import com.example.digikala.data.models.category.CategoriesData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesDataSource {
    @GET("categories/")
    suspend fun getCategoriesData(): Flow<CategoriesData>
}