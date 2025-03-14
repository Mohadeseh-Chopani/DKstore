package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.HomeDataSourceImp
import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImp(val homeDataSourceImp: HomeDataSourceImp): HomeRepository {
    override fun getHomeData(token: String): Flow<HomePageData> {
        TODO("Not yet implemented")
    }
}