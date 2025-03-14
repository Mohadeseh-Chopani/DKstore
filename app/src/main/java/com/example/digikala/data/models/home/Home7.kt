package com.example.digikala.data.models.home

data class Home7(
    val code: String,
    val description: String,
    val products: List<Product>,
    val products_count: Int,
    val recommendation_code: String,
    val title: String,
    val url: Url
)