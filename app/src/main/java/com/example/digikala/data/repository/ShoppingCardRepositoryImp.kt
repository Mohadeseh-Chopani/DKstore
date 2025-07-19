package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.local.ShoppingCard.ShoppingCardDataSourceImp
import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.models.product.Product
import kotlinx.coroutines.flow.Flow

class ShoppingCardRepositoryImp(val shoppingCardDataSourceImp: ShoppingCardDataSourceImp): ShoppingCardRepository {
    override suspend fun addProductToCard(product: ShoppingCardEntity) {
        return shoppingCardDataSourceImp.addProductToCard(product)
    }

    override suspend fun deleteProductById(productId: Int) {
        return shoppingCardDataSourceImp.deleteProductById(productId)
    }

    override suspend fun getProductsList(userId: String): UserWithSoppingCard? {
        return shoppingCardDataSourceImp.getProductsList(userId)
    }

    override suspend fun addUserToDatabase(user: UserEntity): Long {
        return shoppingCardDataSourceImp.addUserToDatabase(user)
    }

    override suspend fun findProductById(productId: Long): Flow<ShoppingCardEntity?> {
        return shoppingCardDataSourceImp.findProductById(productId)
    }
}