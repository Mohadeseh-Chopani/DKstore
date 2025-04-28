package com.example.digikala.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.digikala.view.ProductViewModel

object LocalProvider {
    val LocalProductViewModel = staticCompositionLocalOf<ProductViewModel> {
        error("ProductViewModel not provided")
    }
}