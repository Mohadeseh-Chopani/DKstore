package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.remote.CategoriesDataSourceImp
import com.example.digikala.data.models.category.CategoriesData
import kotlinx.coroutines.flow.Flow

class CategoriesRepositoryImp(val categoriesDataSourceImp: CategoriesDataSourceImp): CategoriesRepository {
    override suspend fun getCategoriesData(): Flow<CategoriesData> {
        return categoriesDataSourceImp.getCategoriesData()
    }
}