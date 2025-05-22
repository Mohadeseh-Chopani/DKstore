package com.example.digikala.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.product.AttributeInformationData
import com.example.digikala.data.models.product.Attributes
import com.example.digikala.data.models.product.ProductPageData
import com.example.digikala.data.repository.ProductRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductViewModel(val productRepositoryImp: ProductRepositoryImp): ViewModel() {

    val _productData = MutableStateFlow<NetworkState<ProductPageData>>(NetworkState.Loading)
    val productData: StateFlow<NetworkState<ProductPageData>> get() = _productData

    fun getProductData(id: Long) {
        viewModelScope.launch {
            try {
                productRepositoryImp.getProductContent(id)
                    .onStart {
                        _productData.value = NetworkState.Loading
                    }
                    .catch {
                        _productData.value = NetworkState.Failure(it)
                    }
                    .collect {response ->
                        _productData.value = NetworkState.Success(response)
                    }
            } catch (e: Exception) {
                _productData.value = NetworkState.Failure(e)
            }
        }
    }


    val _attributeInformation = MutableStateFlow<NetworkState<AttributeInformationData>>(NetworkState.Loading)
    val attributeInformation: StateFlow<NetworkState<AttributeInformationData>> get() = _attributeInformation

    fun getAttributeData(id:Long) {
        viewModelScope.launch {
            productRepositoryImp.getAttributeData(id)
                .onStart {  }
                .catch {
                    _attributeInformation.value = NetworkState.Failure(it)
                    Log.e("MOX", "getAttributeData: " + it.message)
                }
                .collect {response->
                    _attributeInformation.value = NetworkState.Success(response)
                }
        }
    }
}