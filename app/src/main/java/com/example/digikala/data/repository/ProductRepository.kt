package com.example.digikala.data.repository

import com.example.digikala.data.models.product.AttributeInformationData
import com.example.digikala.data.models.product.ProductPageData
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductRepository {
    @GET("product/")
    suspend fun getProductContent(
        @Query("id") id: Long
    ): Flow<ProductPageData>

    @GET("product/specifications")
    suspend fun getAttributeData(
        @Query("id") id: Long
    ): Flow<AttributeInformationData>
}