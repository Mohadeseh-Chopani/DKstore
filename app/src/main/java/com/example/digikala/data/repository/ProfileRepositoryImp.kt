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
}