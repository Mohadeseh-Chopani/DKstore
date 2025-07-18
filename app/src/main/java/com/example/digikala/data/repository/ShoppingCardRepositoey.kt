package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.models.product.Product

interface ShoppingCardRepository {
    suspend fun addProductToCard(product: ShoppingCardEntity)

    suspend fun deleteProductById(productId: Int)

    suspend fun getProductsList(userId: String): UserWithSoppingCard?

    suspend fun addUserToDatabase(user: UserEntity)

}