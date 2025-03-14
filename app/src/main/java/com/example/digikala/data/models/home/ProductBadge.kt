package com.example.digikala.data.models.home

data class ProductBadge(
    val icon: String,
    val icon_color: String,
    val image: String,
    val text: String,
    val text_color: String,
    val id: Int,
    val payload: Payload,
    val priority: Int,
    val slot: String,
    val type: String
)