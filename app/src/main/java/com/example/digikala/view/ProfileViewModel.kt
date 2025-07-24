package com.example.digikala.view

import SessionManager
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.dataSource.local.UserEntity
import com.example.digikala.data.dataSource.local.UserWithSoppingCard
import com.example.digikala.data.repository.ProfileRepositoryImp
import com.example.digikala.utils.RegistrationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class ProfileViewModel(val profileRepositoryImp: ProfileRepositoryImp,
                       private val sessionManager: SessionManager
): ViewModel() {

    private val _userCart = MutableStateFlow<UserWithSoppingCard?>(null)
    val userCart = _userCart.asStateFlow()

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.IDLE)
    val registrationState = _registrationState.asStateFlow()

    fun addUserToDatabase(user: UserEntity) {

        viewModelScope.launch {
            _registrationState.value = RegistrationState.LOADING

            try {
                val rowId = profileRepositoryImp.addUserToDatabase(user)

                if (rowId > 0) {
                    sessionManager.saveUserId(user.phoneNumber)
                    _registrationState.value = RegistrationState.SUCCESS
                } else {
                    _registrationState.value = RegistrationState.FAILURE
                }

            } catch (e: SQLiteConstraintException) {
                _registrationState.value = RegistrationState.USER_EXISTS

            } catch (e: Exception) {
                _registrationState.value = RegistrationState.FAILURE
            }
        }
    }

    fun resetRegistrationState() {
        _registrationState.value = RegistrationState.IDLE
    }

    val isUserLoggedIn: StateFlow<Boolean?> =
        sessionManager.getUserIdFlow
            .flatMapLatest { userId ->
                if (userId == null) {
                    flowOf(false)
                } else {
                    profileRepositoryImp.getUserFromDatabase(userId)
                        .map { user -> user != null }
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )


    val userData: StateFlow<UserEntity?> =
        sessionManager.getUserIdFlow
            .flatMapLatest { userId ->
                if (userId == null) {
                    flowOf(null)
                } else {
                    profileRepositoryImp.getUserFromDatabase(userId)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )


    fun logout() {
        viewModelScope.launch {
            sessionManager?.clearSession()
        }
    }
}