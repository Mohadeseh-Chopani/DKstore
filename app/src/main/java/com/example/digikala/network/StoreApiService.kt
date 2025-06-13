package com.example.digikala.network

import com.example.digikala.data.models.category.CategoriesData
import com.example.digikala.data.models.product.AttributeInformationData
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreApiService {
    @GET("home")
    suspend fun getHomePageData(
    ): Response<ResponseBody>

    @GET("product/")
    suspend fun getProductContent(
        @Query("id") id: Long
    ): Response<ResponseBody>

    @GET("product/specifications")
    suspend fun getAttributeData(
        @Query("id") id: Long
    ): Response<ResponseBody>

    @GET("categories/")
    suspend fun getCategoriesData(): Response<ResponseBody>

    @GET("search/category-{categorySlug}/{brandSlug}/")
    suspend fun getProductsByCategoryAndBrand(
        @Path("categorySlug") categorySlug: String,
        @Path("brandSlug") brandSlug: String
    ): Response<ResponseBody>

    @GET("search/")
    suspend fun getSearchData(
        @Query("q") query: String,
    ): Response<ResponseBody>
}