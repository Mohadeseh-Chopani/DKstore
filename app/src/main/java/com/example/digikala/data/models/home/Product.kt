package com.example.digikala.data.models.home

data class Product(
    val brand: Brand,
    val category_id: Int,
    val category_title: String,
    val default_variant_id: Int,
    val digiplus: Digiplus,
    val id: Int,
    val images: Images,
    val parameters: Parameters,
    val price: Price,
    val product_badge: ProductBadge,
    val product_badges: List<ProductBadge>,
    val product_type: String,
    val properties: Properties,
    val rating: Rating,
    val second_default_variant_id: Int,
    val status: String,
    val title_en: String,
    val title_fa: String,
    val warehouse_stock: Int,
    val test_title_fa: String
)