package com.example.digikala.utils

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.digikala.R

data class BottomNavigationItem(
    val label: String = R.string.home.toString(),
    val icon: ImageVector = Icons.Default.Home,
    val route: String = ""
) {
    fun bottomNavigationItem(context: Context): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = context.getString(R.string.home),
                Icons.Default.Home,
                Const.HOME
            ),
            BottomNavigationItem(
                context.getString(R.string.categorize),
                Icons.Default.Check,
                Const.CATEGORIES
            ),
            BottomNavigationItem(
                context.getString(R.string.shopping_cart),
                Icons.Default.ShoppingCart,
                Const.SHOPPING_CART
            ),
            BottomNavigationItem(
                context.getString(R.string.my_account),
                Icons.Default.AccountCircle,
                Const.PROFILE
            )
        )
    }
}