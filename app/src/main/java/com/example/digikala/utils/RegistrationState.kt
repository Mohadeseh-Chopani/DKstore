package com.example.digikala.utils

import kotlinx.coroutines.flow.MutableStateFlow

enum class RegistrationState {
    IDLE,       // وضعیت اولیه
    LOADING,    // در حال پردازش
    SUCCESS,    // ثبت نام موفق
    USER_EXISTS,// کاربر تکراری
    FAILURE     // خطای عمومی
}