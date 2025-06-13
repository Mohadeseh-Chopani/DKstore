package com.example.digikala.data.models.home

data class HomeScreenState(
    val homeData: HomePageData? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategoryIndex: Int = 0,
)