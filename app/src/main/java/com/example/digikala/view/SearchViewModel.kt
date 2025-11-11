package com.example.digikala.view

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.search.SearchFilter
import com.example.digikala.data.models.search.FilterOption
import com.example.digikala.data.models.search.Filters
import com.example.digikala.data.models.search.PriceRange
import com.example.digikala.data.models.search.ProductsItem
import com.example.digikala.data.models.search.Result
import com.example.digikala.data.models.search.SearchData
import com.example.digikala.data.repository.SearchRepositoryImp
import com.example.digikala.utils.NetworkState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class SearchViewModel(val searchRepositoryImp: SearchRepositoryImp) : ViewModel() {

    var currentPage = 1
    var isLoading = false
    var isLastPage = false

    var searchText = ""

    val cachedProducts = mutableStateListOf<ProductsItem>()

    var _filters = MutableStateFlow<List<SearchFilter>?>(emptyList())
    val filters: StateFlow<List<SearchFilter>?> get() = _filters


    val _searchData = MutableStateFlow<NetworkState<SearchData>>(NetworkState.Uninitialized)
    val searchData: StateFlow<NetworkState<SearchData>> get() = _searchData

    fun getSearchData(query: String) {
        if (isLoading || isLastPage) return

        isLoading = true

        searchText = query

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
                        _filters.value = response.result?.toUnifiedGroups()
                    } else {
                        val currentProducts =
                            (_searchData.value as? NetworkState.Success)?.data?.result?.products ?: emptyList()
                        val newProducts = response.result?.products
                        val combinedProducts = currentProducts + newProducts

                        val updatedSearchData = response.copy(
                            result = response.result?.copy(products = combinedProducts as List<ProductsItem>)
                        )

                        _searchData.value = NetworkState.Success(updatedSearchData)
                        _filters.value = response.result?.toUnifiedGroups()

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


    fun Result.toUnifiedGroups(): List<SearchFilter> {
        val filtersObject = this.filters ?: return emptyList()
        val items = mutableListOf<SearchFilter>()

        fun <T> mapOptions(optionsList: List<T>?, idSelector: (T) -> String, titleSelector: (T) -> String?): List<FilterOption> {
            return optionsList.orEmpty().mapNotNull { opt ->
                titleSelector(opt)?.let { title ->
                    FilterOption(
                        id = idSelector(opt),
                        title_fa = title,
                        is_selected = mutableStateOf(false)
                    )
                }
            }
        }

        filtersObject.categories?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = mapOptions(filter.options, { it.id.toString() }, { it.title_fa })
                )
            )
        }

        filtersObject.brands?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = mapOptions(filter.options, { it.id.toString() }, { it.title_fa })
                )
            )
        }

        filtersObject.price?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    // برای اسلایدر قیمت، می‌توانی min و max را در description ذخیره کنی
                    // یا یک FilterOption خاص بسازی
                    options = listOf(
                        FilterOption(
                            id = "price_range",
                            title_fa = "محدوده قیمت",
                            description = "${filter.options?.min ?: 0},${filter.options?.max ?: 0}",
                            is_selected = mutableStateOf(false)
                        )
                    )
                )
            )
        }

        filtersObject.color_palettes?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = filter.options.orEmpty().map { opt ->
                        FilterOption(
                            id = opt.id.toString(),
                            title_fa = opt.title_fa ?: "",
                            title_en = opt.title_en,
                            icon = opt.image_url,
                            icon_color = opt.code,
                            is_selected = mutableStateOf(false)
                        )
                    }
                )
            )
        }

        filtersObject.seller_types?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = mapOptions(filter.options, { it.id.orEmpty() }, { it.title_fa })
                )
            )
        }

        filtersObject.attributes?.options?.forEach { subFilter ->
            items.add(
                SearchFilter(
                    title = subFilter.title,
                    type = subFilter.type,
                    options = mapOptions(subFilter.options, { it.id.toString() }, { it.title_fa })
                )
            )
        }

        filtersObject.has_selling_stock?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = mapOptions(filter.options, { "has_selling_stock" }, { it.title_fa })
                )
            )
        }

        filtersObject.has_ready_to_shipment?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = mapOptions(filter.options, { "has_ready_to_shipment" }, { it.title_fa })
                )
            )
        }

        filtersObject.digiplus?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = filter.options.orEmpty().map { opt ->
                        FilterOption(
                            id = opt.id.orEmpty(),
                            title_fa = opt.title_fa ?: "",
                            icon = opt.icon,
                            icon_color = opt.icon_color,
                            is_selected = mutableStateOf(false)
                        )
                    }
                )
            )
        }

        filtersObject.has_jet_delivery?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = listOf(
                        FilterOption(
                            id = "has_jet_delivery",
                            title_fa = filter.title ?: "ارسال سریع",
                            icon = filter.icon,
                            icon_color = filter.icon_color,
                            is_selected = mutableStateOf(false)
                        )
                    )
                )
            )
        }

        filtersObject.has_ship_by_seller?.let { filter ->
            items.add(
                SearchFilter(
                    title = filter.title,
                    type = filter.type,
                    options = listOf(
                        FilterOption(
                            id = "has_ship_by_seller",
                            title_fa = filter.title ?: "ارسال فروشنده",
                            icon = filter.icon,
                            icon_color = filter.icon_color,
                            is_selected = mutableStateOf(false)
                        )
                    )
                )
            )
        }
        // ... به همین ترتیب سایر فیلترها (only_fresh, has_ship_by_seller) را اضافه کن ...

        Log.d("MOX", "toUnifiedGroups: " + Gson().toJson(items))
        return items
    }
}