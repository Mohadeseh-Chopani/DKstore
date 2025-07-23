package com.example.digikala.data.dataSource.local.ShoppingCard

import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.models.product.Product
import kotlinx.coroutines.flow.Flow

interface ShoppingCardDataSource {
    suspend fun addProductToCard(product: ShoppingCardEntity)

    suspend fun deleteProductById(productId: Int)

    suspend fun getProductsList(userId: String): UserWithSoppingCard?

    suspend fun findProductById(productId: Long): Flow<ShoppingCardEntity?>
}