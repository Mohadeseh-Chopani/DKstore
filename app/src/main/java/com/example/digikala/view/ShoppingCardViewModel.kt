package com.example.digikala.view

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.models.product.Product
import com.example.digikala.data.repository.ShoppingCardRepositoryImp
import com.example.digikala.utils.RegistrationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShoppingCardViewModel(val shoppingCardRepositoryImp: ShoppingCardRepositoryImp): ViewModel() {

    var isLogin = false
    fun isLogin(login: Boolean) {
        isLogin = login
    }


    private val _isProductInCart = MutableStateFlow(false)
    val isProductInCart: StateFlow<Boolean> = _isProductInCart


    val user = UserEntity(
        "09226237388",
        "1212mch@"
    )

    fun addProductToDatabase(product: ShoppingCardEntity) {
        viewModelScope.launch {

//            shoppingCardRepositoryImp.addUserToDatabase(user)

            shoppingCardRepositoryImp.addProductToCard(product)
        }
    }




    fun checkIfProductIsInCart(productId: Long) {
        viewModelScope.launch {
            shoppingCardRepositoryImp.findProductById(productId)
                .collect { productEntity ->
                    _isProductInCart.value = productEntity != null
                }
        }
    }
}
