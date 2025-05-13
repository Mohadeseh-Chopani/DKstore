package com.example.digikala.data.models.product

data class AttributeInformationData (
    val status: Int,
    val result: List<DetailSection>
)

data class DetailSection(
    val title: String,
    val attributes: List<Attributes>? = null
)

data class Attributes(
    val title: String,
    val values: Any
)