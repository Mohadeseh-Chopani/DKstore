package com.example.digikala.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.digikala.view.CategoriesViewModel
import com.example.digikala.view.ProductViewModel

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
}