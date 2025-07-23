package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.remote.HomePageDataSourceImp
import com.example.digikala.data.models.home.HomePageData
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImp(private val HomePageDataSourceImp: HomePageDataSourceImp): HomeRepository {

    override fun getHomePageData(): Flow<HomePageData> {
        return HomePageDataSourceImp.getHomePageData()
    }
}