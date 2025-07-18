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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShoppingCardViewModel(val shoppingCardRepositoryImp: ShoppingCardRepositoryImp): ViewModel() {

    var isLogin = false
    fun isLogin(login: Boolean) {
        isLogin = login
    }

    private val _userCart = MutableStateFlow<UserWithSoppingCard?>(null)
    val userCart = _userCart.asStateFlow()

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.IDLE)
    val registrationState = _registrationState.asStateFlow()


    val user = UserEntity(
        "09226237388",
        "1212mch@"
    )

    fun addProductToDatabase(product: ShoppingCardEntity) {
        viewModelScope.launch {

            shoppingCardRepositoryImp.addUserToDatabase(user)

            shoppingCardRepositoryImp.addProductToCard(product)
        }
    }

    fun addUserToDatabase(user: UserEntity) {

        viewModelScope.launch {
            // ۳. قبل از شروع عملیات، وضعیت را به "در حال پردازش" تغییر دهید
            _registrationState.value = RegistrationState.LOADING

            try {
                // ۴. تابع ریپازیتوری را فراخوانی کنید
                // فرض می‌کنیم shoppingCardRepositoryImp.addUserToDatabase(user) یک Long برمی‌گرداند
                val rowId = shoppingCardRepositoryImp.addUserToDatabase(user)

                if (rowId > 0) {
                    // اگر rowId مثبت بود، عملیات موفقیت‌آمیز بوده است
                    _registrationState.value = RegistrationState.SUCCESS
                } else {
                    // اگر به هر دلیلی موفق نبود (این حالت کمتر پیش می‌آید)
                    _registrationState.value = RegistrationState.FAILURE
                }

            } catch (e: SQLiteConstraintException) {
                // ۵. اگر خطای کلید خارجی رخ داد، یعنی کاربر تکراری است
                _registrationState.value = RegistrationState.USER_EXISTS

            } catch (e: Exception) {
                // ۶. برای هر خطای پیش‌بینی نشده دیگر
                _registrationState.value = RegistrationState.FAILURE
            }
        }
    }

    fun resetState() {
        _registrationState.value = RegistrationState.IDLE
    }
}
