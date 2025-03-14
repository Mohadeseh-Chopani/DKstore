package com.example.digikala.data.models.home

data class Digiplus(
    val fast_shipping_text: String,
    val is_jet_eligible: Boolean,
    val service_list: List<Service>,
    val services: List<String>,
    val services_summary: List<String>
)