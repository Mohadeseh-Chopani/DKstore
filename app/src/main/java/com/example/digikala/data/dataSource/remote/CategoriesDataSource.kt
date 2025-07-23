package com.example.digikala.data.dataSource.remote

import com.example.digikala.data.models.category.CategoriesData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CategoriesDataSource {
    @GET("categories/")
    suspend fun getCategoriesData(): Flow<CategoriesData>
}