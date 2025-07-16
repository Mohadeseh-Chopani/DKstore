package com.example.digikala.view

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.product.Product
import com.example.digikala.data.models.search.ProductsItem
import com.example.digikala.data.models.search.SearchData
import com.example.digikala.data.repository.SearchRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(val searchRepositoryImp: SearchRepositoryImp): ViewModel() {

    var currentPage = 1
    var isLoading = false
    var isLastPage = false

    val cachedProducts = mutableStateListOf<ProductsItem>()

    val _searchData = MutableStateFlow<NetworkState<SearchData>>(NetworkState.Loading)
    val searchData: StateFlow<NetworkState<SearchData>> get() = _searchData

    fun getSearchData(query: String) {
        if (isLoading || isLastPage) return

        isLoading = true

        viewModelScope.launch {
            searchRepositoryImp.getSearchData(query, currentPage)
                .onStart {
                    _searchData.value = NetworkState.Loading
                }
                .catch { throwable ->
                    _searchData.value = NetworkState.Failure(throwable)
                    isLoading = false
                }
                .collect { response ->
                    if (currentPage == 1) {
                        _searchData.value = NetworkState.Success(response)
                        response.result?.products?.mapNotNull { it as? ProductsItem }?.let {
                            cachedProducts.addAll(it)
                        }
                    } else {
                        val currentProducts =
                            (_searchData.value as? NetworkState.Success)?.data?.result?.products ?: emptyList()
                        val newProducts = response.result?.products
                        val combinedProducts = currentProducts + newProducts

                        val updatedSearchData = response.copy(
                            result = response.result?.copy(products = combinedProducts as List<ProductsItem>)
                        )

                        _searchData.value = NetworkState.Success(updatedSearchData)
                        Log.i("MOX", "getSearchData: "+ updatedSearchData.result?.products?.size)
                        response.result?.products?.mapNotNull { it as? ProductsItem }?.let {
                            cachedProducts.addAll(it)
                        }
                    }

                    if (response.result?.products?.isEmpty() == true) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }

                    isLoading = false
                }
        }
    }
}