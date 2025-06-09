package com.example.digikala.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.search.SearchData
import com.example.digikala.data.repository.SearchRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(val searchRepositoryImp: SearchRepositoryImp): ViewModel() {

    val _searchData = MutableStateFlow<NetworkState<SearchData>>(NetworkState.Loading)
    val searchData: StateFlow<NetworkState<SearchData>> get() = _searchData

    fun getSearchData(query: String) {
        viewModelScope.launch {
            searchRepositoryImp.getSearchData(query)
                .onStart {
                    _searchData.value = NetworkState.Loading
                }
                .catch { throwable ->
                    _searchData.value = NetworkState.Failure(throwable)
                }
                .collect {response ->
                    _searchData.value = NetworkState.Success(response)
                }
        }
    }
}