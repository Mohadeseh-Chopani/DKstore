package com.example.digikala.data.dataSource.local.ShoppingCard

import com.example.digikala.data.dataSource.local.Dao
import com.example.digikala.data.dataSource.local.UserEntity
import kotlinx.coroutines.flow.Flow

class ProfileDataSourceImp(val dao: Dao): ProfileDataSource {
    override suspend fun getUserFromDatabase(userId: String): Flow<UserEntity?> {
        return dao.getUserById(userId)
    }

    override suspend fun deleteAllUser() {
        return dao.deleteAllUser()
    }

    override fun getUserId(): Flow<String> {
        return dao.getUserId()
    }

    override suspend fun addUserToDatabase(user: UserEntity): Long {
        return dao.insertUser(user)
    }
}