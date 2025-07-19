package com.example.digikala.utils

import kotlinx.coroutines.flow.MutableStateFlow

enum class RegistrationState {
    IDLE,       // first state
    LOADING,    // prosecing
    SUCCESS,    // login
    USER_EXISTS,// repeat user
    FAILURE     // public error
}