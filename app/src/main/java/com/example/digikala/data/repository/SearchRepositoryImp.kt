package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.remote.SearchDataSourceImp
import com.example.digikala.data.models.search.SearchData
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImp(val searchDataSourceImp: SearchDataSourceImp): SearchRepository {
    override suspend fun getSearchData(query: String, page: Int): Flow<SearchData> {
        return searchDataSourceImp.getSearchData(query, page)
    }
}