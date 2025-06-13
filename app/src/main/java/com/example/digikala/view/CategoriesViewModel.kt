package com.example.digikala.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.category.CategoriesData
import com.example.digikala.data.repository.CategoriesRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CategoriesViewModel(val categoriesRepositoryImp: CategoriesRepositoryImp): ViewModel() {

    init {
//        getCategoriesData()
    }

    val _categoriesData = MutableStateFlow<NetworkState<CategoriesData>>(NetworkState.Loading)
    val categoriesData: StateFlow<NetworkState<CategoriesData>> get() = _categoriesData

    fun getCategoriesData() {
        viewModelScope.launch {
            categoriesRepositoryImp.getCategoriesData()
                .onStart {
                    _categoriesData.value = NetworkState.Loading
                }
                .catch { throwable->
                    _categoriesData.value = NetworkState.Failure(throwable)
                }
                .collect {response->
                    _categoriesData.value = NetworkState.Success(response)
                }
        }
    }
}