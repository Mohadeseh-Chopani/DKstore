package com.example.digikala.data.dataSource.local

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSoppingCard(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "phoneNumber",
        entityColumn = "userId"
    )
    val shoppingList: List<ShoppingCardEntity>
)