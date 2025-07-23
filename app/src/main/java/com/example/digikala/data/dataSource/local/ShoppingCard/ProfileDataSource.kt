package com.example.digikala.data.dataSource.local.ShoppingCard

import com.example.digikala.data.dataSource.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    suspend fun getUserFromDatabase(userId: String): Flow<UserEntity?>
    suspend fun deleteAllUser()
    fun getUserId(): Flow<String>

    suspend fun addUserToDatabase(user: UserEntity): Long
}