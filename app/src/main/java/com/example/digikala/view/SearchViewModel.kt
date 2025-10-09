package com.example.digikala.view

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.models.search.SearchFilter
import com.example.digikala.data.models.search.FilterOption
import com.example.digikala.data.models.search.Filters
import com.example.digikala.data.models.search.ProductsItem
import com.example.digikala.data.models.search.SearchData
import com.example.digikala.data.repository.SearchRepositoryImp
import com.example.digikala.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

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
                        _filters.value = response.result?.filters?.toUnifiedGroups()
                    } else {
                        val currentProducts =
                            (_searchData.value as? NetworkState.Success)?.data?.result?.products ?: emptyList()
                        val newProducts = response.result?.products
                        val combinedProducts = currentProducts + newProducts

                        val updatedSearchData = response.copy(
                            result = response.result?.copy(products = combinedProducts as List<ProductsItem>)
                        )

                        _searchData.value = NetworkState.Success(updatedSearchData)
                        _filters.value = response.result?.filters?.toUnifiedGroups()
//
//                        Log.d("MOX", "getSearchData: "+ _filters.value?.size)
//
//                        Log.i("MOX", "getSearchData: " + updatedSearchData.result?.products?.size)
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


    fun Filters.toUnifiedGroups(): List<SearchFilter> {
        val groups = mutableListOf<SearchFilter>()

        categories?.let { cat ->
            groups += SearchFilter(
                type = cat.type.orEmpty(),
                title = cat.title.orEmpty(),
                options = cat.options?.map { opt ->
                    FilterOption(
                        id = opt.id.toString(),
                        titleFa = opt.title_fa.orEmpty(),
                        titleEn = opt.title_en,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        brands?.let { br ->
            groups += SearchFilter(
                type = br.type.orEmpty(),
                title = br.title.orEmpty(),
                options = br.options?.map { opt ->
                    FilterOption(
                        id = opt.id.toString(),
                        titleFa = opt.title_fa.orEmpty(),
                        titleEn = opt.title_en,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        price?.let { pr ->
            groups += SearchFilter(
                type = pr.type.orEmpty(),
                title = pr.title.orEmpty(),
                options = listOf(
                    FilterOption(
                        id = "price",
                        titleFa = "حداکثر: ${pr.options?.max ?: 0}",
                        titleEn = null,
                        isSelected = mutableStateOf(false)
                    )
                )
            )
        }

        seller_types?.let { st ->
            groups += SearchFilter(
                type = st.type.orEmpty(),
                title = st.title.orEmpty(),
                options = st.options?.map { opt ->
                    FilterOption(
                        id = opt.id.orEmpty(),
                        titleFa = opt.title_fa.orEmpty(),
                        titleEn = opt.title_en,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        has_selling_stock?.let { hs ->
            groups += SearchFilter(
                type = hs.type.orEmpty(),
                title = hs.title.orEmpty(),
                options = hs.options?.map { opt ->
                    FilterOption(
                        id = "stock",
                        titleFa = opt.title_fa.orEmpty(),
                        titleEn = opt.title_en,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        digiplus?.let { dp ->
            groups += SearchFilter(
                type = dp.type.orEmpty(),
                title = dp.title.orEmpty(),
                options = dp.options?.map { opt ->
                    FilterOption(
                        id = opt.id.orEmpty(),
                        titleFa = opt.title_fa.orEmpty(),
                        titleEn = opt.title_en,
                        icon = opt.icon,
                        iconColor = opt.icon_color,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        has_jet_delivery?.let { jetDelivery ->
            groups += SearchFilter(
                type = jetDelivery.type.orEmpty(),
                title = jetDelivery.title.orEmpty(),
                options = listOf(
                    FilterOption(
                        id = "jet_delivery",
                        titleFa = jetDelivery.title ?: "ارسال فوری",
                        icon = jetDelivery.icon,
                        iconColor = jetDelivery.icon_color,
                        isSelected = mutableStateOf(false)
                    )
                )
            )
        }

        only_fresh?.let { fresh ->
            groups += SearchFilter(
                type = fresh.type.orEmpty(),
                title = fresh.title.orEmpty(),
                options = fresh.options?.map { opt ->
                    FilterOption(
                        id = "fresh",
                        titleFa = opt.title_fa ?: "فقط سوپرمارکتی",
                        titleEn = opt.title_en,
                        icon = fresh.icon,
                        iconColor = fresh.icon_color,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        has_ship_by_seller?.let { hss ->
            groups += SearchFilter(
                type = hss.type.orEmpty(),
                title = hss.title.orEmpty(),
                options = hss.options?.map { opt ->
                    FilterOption(
                        id = "ship_by_seller",
                        titleFa = opt.title_fa.orEmpty(),
                        titleEn = opt.title_en,
                        icon = hss.icon,
                        iconColor = hss.icon_color,
                        description = hss.description,
                        isSelected = mutableStateOf(false)
                    )
                }.orEmpty()
            )
        }

        return groups

    }


//    fun toggleFilter(filterId: String) {
//        _filters.value = _filters.value.map { item ->
//            if (item.id == filterId) {
//                item.copy(isSelected = !item.isSelected)
//            } else item
//        }.sortedWith(compareByDescending<FilterItem> { it.isSelected }
//            .thenBy { it.originalIndex })
//    }
}