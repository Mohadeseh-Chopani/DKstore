package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getUserFromDatabase(userId: String): Flow<UserEntity?>

    suspend fun deleteAllUser()

    fun getUserId(): Flow<String>

    suspend fun addUserToDatabase(user: UserEntity): Long

    suspend fun updateUserAccount(name: String, nationalCode: String, address: String, phoneNumber: String)

    suspend fun deleteUserById(userId: String)

}