package com.example.digikala.data.models.category

data class CategoriesData(
    val status: Int,
    val result: List<Result>
)

data class Result(
    val id: Int,
    val title: String,
    val url: Url,
    val plp_url: PlpUrl,
    val row_number: Int,
    val icon: String,
    val children: List<Children>
)

data class Url(
    val url: String,
    val page: String,
    val params: Params
)

data class Params(
    val url: String
)

data class PlpUrl(
    val url: String,
    val page: String,
    val params: ParamsUrl
)

data class ParamsUrl(
    val url: String
)

data class Children(
    val id: Int,
    val title: String,
    val url: ChildrenUrl,
    val column_number: Int,
    val row_number: Int,
    val children: List<InnerChildren>
)

data class ChildrenUrl(
    val url: String,
    val page: String,
    val params: ChildrenParamsUrl
)

data class ChildrenParamsUrl(
    val url: String
)

data class InnerChildren(
    val id: Int,
    val title: String,
    val url: InnerChildrenUrl,
    val row_number: Int,
    val image: String
)

data class InnerChildrenUrl(
    val url: String,
    val page: String,
    val params: InnerChildrenParamsUrl
)

data class InnerChildrenParamsUrl(
    val brand_id: Int,
    val category_id: Int
)