package com.example.digikala.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.home.HomePageData
import com.example.digikala.data.repository.HomeRepository
import com.example.digikala.data.repository.HomeRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(val homeRepositoryImp: HomeRepositoryImp) : ViewModel() {

    val _homeData = MutableStateFlow<NetworkState<HomePageData>>(NetworkState.Loading)
    val homeData: StateFlow<NetworkState<HomePageData>> get() = _homeData
    fun getHomeData(token: String) {
        viewModelScope.launch {
            try {
                homeRepositoryImp.getHomePageData(token)
                    .onStart {
                        _homeData.value = NetworkState.Loading
                    }
                    .catch { e ->
                        _homeData.value = NetworkState.Failure(e)
                    }
                    .collect { response ->
                        _homeData.value = NetworkState.Success(response)
                    }
            } catch (e: Exception) {
                _homeData.value = NetworkState.Failure(e)
            }
        }
    }
}