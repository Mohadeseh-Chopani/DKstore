package com.example.digikala.data.dataSource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDB")
data class UserEntity(
    @PrimaryKey() val phoneNumber: String,
    @ColumnInfo(name = "password") val password: String?,
)