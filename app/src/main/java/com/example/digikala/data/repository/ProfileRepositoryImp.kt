package com.example.digikala.data.repository

import com.example.digikala.data.dataSource.local.ShoppingCard.ProfileDataSourceImp
import com.example.digikala.data.dataSource.local.UserEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImp(val profileDataSourceImp: ProfileDataSourceImp): ProfileRepository {
    override suspend fun getUserFromDatabase(userId: String): Flow<UserEntity?> {
        return profileDataSourceImp.getUserFromDatabase(userId)
    }

    override suspend fun deleteAllUser() {
        return profileDataSourceImp.deleteAllUser()
    }

    override fun getUserId(): Flow<String> {
        return profileDataSourceImp.getUserId()
    }

    override suspend fun addUserToDatabase(user: UserEntity): Long {
        return profileDataSourceImp.addUserToDatabase(user)
    }

    override suspend fun updateUserAccount(name: String, nationalCode: String, address: String, phoneNumber: String) {
        return profileDataSourceImp.updateUserAccount(name, nationalCode, address, phoneNumber)
    }

    override suspend fun deleteUserById(userId: String) {
        return profileDataSourceImp.deleteUserById(userId)
    }
}