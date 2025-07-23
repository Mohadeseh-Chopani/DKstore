package com.example.digikala.data.dataSource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.digikala.data.models.product.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    // This is the function you'll call to get a user and all their cart items
    @Transaction
    @Query("SELECT * FROM USERDB WHERE phoneNumber = :userId")
    suspend fun getUserWithShoppingCart(userId: String): UserWithSoppingCard?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity): Long

    @Query("DELETE FROM USERDB WHERE phoneNumber = :userId")
    suspend fun deleteUserById(userId: Long)

    @Query("DELETE FROM USERDB ")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM USERDB WHERE phoneNumber = :userId")
    fun getUserById(userId: String): Flow<UserEntity?>

    @Insert
    suspend fun insertProduct(products: ShoppingCardEntity)

    @Query("DELETE FROM shoppingCardDB WHERE productId = :productId")
    suspend fun deleteProductById(productId: Int)

    @Query("SELECT * FROM shoppingCardDB WHERE productId = :id")
    fun findProductById(id: Long): Flow<ShoppingCardEntity?>

    @Query("SELECT phoneNumber FROM USERDB ")
    fun getUserId(): Flow<String>
}