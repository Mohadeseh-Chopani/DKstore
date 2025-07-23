package com.example.digikala.data.dataSource.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.digikala.data.models.product.Product

@Entity (
    tableName = "shoppingCardDB",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["phoneNumber"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class ShoppingCardEntity(
    @PrimaryKey var productId: Long,
    var products: Product,
    var userId: String,
    var count: Int
)