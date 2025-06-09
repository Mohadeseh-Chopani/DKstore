package com.example.digikala.data.dataSource

import com.example.digikala.data.models.search.SearchData
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchDataSource {
//    @GET("search/category-{categorySlug}/{brandSlug}/")
//    suspend fun getProductsByCategoryAndBrand(
//        @Path("categorySlug") categorySlug: String,
//        @Path("brandSlug") brandSlug: String
//    ): Flow<>

    @GET("search/")
    suspend fun getSearchData(
        @Query("q") query: String,
    ): Flow<SearchData>
}