package com.example.digikala.view

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.data.models.home.HomeScreenEvent
import com.example.digikala.data.models.home.HomeScreenState
import com.example.digikala.data.repository.HomeRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(val homeRepositoryImp: HomeRepositoryImp) : ViewModel() {

    val productListState = LazyListState()

    val homeState: StateFlow<HomeScreenState> =
            homeRepositoryImp.getHomePageData()
                .map<HomePageData, NetworkState<HomePageData>> { NetworkState.Success(it) }
                .onStart { emit(NetworkState.Loading) }
                .catch { emit(NetworkState.Failure(it)) }
                .map { networkState ->
                    when (networkState) {
                        is NetworkState.Loading -> HomeScreenState(isLoading = true)
                        is NetworkState.Success -> HomeScreenState(homeData = networkState.data)
                        is NetworkState.Failure -> HomeScreenState(error = "خطا در دریافت اطلاعات")
                        is NetworkState.UnSuccess -> HomeScreenState(error = "عملیات ناموفق بود")
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(300000),
                    initialValue = HomeScreenState(isLoading = true)
                )
}