package com.example.digikala.view

import SessionManager
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.models.product.Product
import com.example.digikala.data.repository.ShoppingCardRepositoryImp
import com.example.digikala.utils.RegistrationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShoppingCardViewModel(
    val shoppingCardRepositoryImp: ShoppingCardRepositoryImp,
    private val sessionManager: SessionManager
) : ViewModel() {

    var isLogin = false
    fun isLogin(login: Boolean) {
        isLogin = login
    }


    private val _isProductInCart = MutableStateFlow(false)
    val isProductInCart: StateFlow<Boolean> = _isProductInCart


    fun addProductToDatabase(product: ShoppingCardEntity) {
        viewModelScope.launch {
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

    fun deleteProductById(productId: Long) {
        viewModelScope.launch {
            shoppingCardRepositoryImp.deleteProductById(productId)
        }
    }


    fun updateProductCount(productId: Long, newCount: Int) {
        viewModelScope.launch {
            shoppingCardRepositoryImp.updateProductCount(productId, newCount)
        }
    }

    val shoppingCartItems: StateFlow<List<ShoppingCardEntity>> =
        sessionManager.getUserIdFlow
            .flatMapLatest { userId ->
                if (userId == null) {
                    flowOf(emptyList())
                } else {
                    shoppingCardRepositoryImp.getProductsList(userId)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}
