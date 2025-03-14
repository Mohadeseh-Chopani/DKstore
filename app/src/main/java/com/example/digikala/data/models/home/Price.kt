package com.example.digikala.data.models.home

data class Price(
    val marketable_stock: Int,
    val order_limit: Int,
    val rrp_price: Int,
    val selling_price: Int,
    val badge: Badge,
    val discount_percent: Int,
    val is_promotion: Boolean,
    val sold_percentage: Int,
    val gold_price_details: GoldPriceDetails,
    val is_incredible: Boolean,
    val price_detail: PriceDetail,
    val timer: String
)