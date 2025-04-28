package com.example.digikala.data.repository

import com.example.digikala.data.models.product.ProductPageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductRepository {
    @GET("product/")
    suspend fun getProductContent(
        @Query("id") id: Long
    ): Flow<ProductPageData>
}