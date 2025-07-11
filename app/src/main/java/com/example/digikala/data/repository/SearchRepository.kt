package com.example.digikala.data.repository

import com.example.digikala.data.models.search.SearchData
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchRepository {

//    @GET("search/category-{categorySlug}/{brandSlug}/")
//    suspend fun getProductsByCategoryAndBrand(
//        @Path("categorySlug") categorySlug: String,
//        @Path("brandSlug") brandSlug: String
//    ): Flow<>

    @GET("search/")
    suspend fun getSearchData(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): Flow<SearchData>
}