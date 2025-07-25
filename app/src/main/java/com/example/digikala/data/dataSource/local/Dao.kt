package com.example.digikala.data.dataSource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    // This is the function you'll call to get a user and all their cart items
    @Transaction
    @Query("SELECT * FROM SHOPPINGCARDDB WHERE userId = :userId")
    fun getUserWithShoppingCart(userId: String): Flow<List<ShoppingCardEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity): Long

    @Query("DELETE FROM USERDB WHERE phoneNumber = :userId")
    suspend fun deleteUserById(userId: String)

    @Query("DELETE FROM USERDB ")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM USERDB WHERE phoneNumber = :userId")
    fun getUserById(userId: String): Flow<UserEntity?>

    @Insert
    suspend fun insertProduct(products: ShoppingCardEntity)

    @Query("DELETE FROM shoppingCardDB WHERE productId = :productId")
    suspend fun deleteProductById(productId: Long)

    @Query("UPDATE shoppingCardDB SET count = :newCount WHERE productId = :productId")
    suspend fun updateProductCount(productId: Long, newCount: Int)

    @Query("SELECT * FROM shoppingCardDB WHERE productId = :id")
    fun findProductById(id: Long): Flow<ShoppingCardEntity?>

    @Query("SELECT phoneNumber FROM USERDB ")
    fun getUserId(): Flow<String>

    @Query("UPDATE userDB SET name = :name, nationalCode = :nationalCode, address = :address WHERE phoneNumber = :phoneNumber")
    suspend fun updateUserAccount(name: String, nationalCode: String, address: String, phoneNumber: String)
}