package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.ProductDataSourceImp
import com.example.digikala.data.models.product.AttributeInformationData
import com.example.digikala.data.models.product.ProductPageData
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImp(val productDataSourceImp: ProductDataSourceImp): ProductRepository {
    override suspend fun getProductContent(id: Long): Flow<ProductPageData> {
        return productDataSourceImp.getProductContent(id)
    }

    override suspend fun getAttributeData(id: Long): Flow<AttributeInformationData> {
        return productDataSourceImp.getAttributeData(id)
    }
}