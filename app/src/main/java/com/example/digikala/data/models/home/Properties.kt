package com.example.digikala.data.models.home

data class Properties(
    val has_best_price: Boolean,
    val has_gift: Boolean,
    val is_ready_to_ship: Boolean,
    val min_price_in_last_month: Int,
    val warehouse_label: String,
    val is_ship_by_seller: Boolean,
    val is_fake: Boolean,
)