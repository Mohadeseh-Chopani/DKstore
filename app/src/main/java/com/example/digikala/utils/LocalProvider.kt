package com.example.digikala.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.digikala.view.CategoriesViewModel
import com.example.digikala.view.ProductViewModel
import com.example.digikala.view.SearchViewModel
import com.example.digikala.view.ShoppingCardViewModel

object LocalProvider {
    val LocalProductViewModel = staticCompositionLocalOf<ProductViewModel> {
        error("ProductViewModel not provided")
    }
    val LocalNavController = staticCompositionLocalOf<NavHostController> {
        error("NavController not provided")
    }

    val LocalCategoriesViewModel = staticCompositionLocalOf<CategoriesViewModel> {
        error("CategoriesViewModel not provided")
    }

    val LocalSearchViewModel = staticCompositionLocalOf<SearchViewModel> {
        error("SearchViewModel not provided")
    }

    val LocalShoppingCardViewModel = staticCompositionLocalOf<ShoppingCardViewModel> {
        error("SearchViewModel not provided")
    }
}