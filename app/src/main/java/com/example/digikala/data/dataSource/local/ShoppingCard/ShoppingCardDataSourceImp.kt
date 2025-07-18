package com.example.digikala.data.dataSource.local.ShoppingCard

import com.example.digikala.data.dataSource.local.Dao
import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.models.product.Product

class ShoppingCardDataSourceImp(val dao:Dao): ShoppingCardDataSource {
    override suspend fun addProductToCard(product: ShoppingCardEntity) {
        return dao.insertProduct(product)
    }

    override suspend fun deleteProductById(productId: Int) {
        return dao.deleteProductById(productId)
    }

    override suspend fun getProductsList(userId: String): UserWithSoppingCard? {
        return dao.getUserWithShoppingCart(userId)
    }

    override suspend fun addUserToDatabase(user: UserEntity) {
        return dao.insertUser(user)
    }
}