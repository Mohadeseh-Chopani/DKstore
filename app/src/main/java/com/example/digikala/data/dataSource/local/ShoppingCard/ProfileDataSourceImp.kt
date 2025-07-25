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

    override suspend fun updateUserAccount(name: String, nationalCode: String, address: String, phoneNumber: String) {
        return dao.updateUserAccount(name, nationalCode, address, phoneNumber)
    }

    override suspend fun deleteUserById(userId: String) {
        return dao.deleteUserById(userId)
    }
}