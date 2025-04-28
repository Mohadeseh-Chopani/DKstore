package com.example.digikala.data.dataSource


import com.example.digikala.data.models.product.ProductPageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDataSource {
    @GET("product/")
    suspend fun getProductContent(
        @Query("id") id: Long
    ): Flow<ProductPageData>
}