package com.example.digikala.data.models.product

data class ProductPageData(
    val status: Double,
    val result: Result,
)

data class Result(
    val product: Product,
    val bigdata_tracker_data: BigdataTrackerData,
    val recommendation: Recommendation
)

data class Product(
    val id: Long,
    val category_id: Double,
    val category_title: String,
    val title_fa: String,
    val rating: Rating,
    val brand: Brand,
    val status: String,
    val images: Images,
    val default_variant_id: Double,
    val second_default_variant_id: Double,
    val parameters: Parameters?,
    val warehouse_stock: Double,
    val product_type: String,
    val properties: Properties,
    val product_badges: List<ProductBadge>?,
    val price: Price,
    val digiplus: Digiplus,
    val product_badge: ProductBadge2,
    val category: Category,
    val suggested_percentage: Double,
    val variants: List<Variant>,
    val review: Review?,
    val pros_and_cons: Any?,
    val breadcrumb: List<Breadcrumb>,
    val videos: List<Video>,
    val comments: Comments?,
    val questions: Questions?,
    val meta: Meta,
    val st_cmp_tacker: StCmpTacker,
    val colorList: colorList
)

data class colorList(
    val title: String,
    val color: String
)

data class Recommendation(
    val related_products: List<RelatedProduct>
)

data class RelatedProduct(
    val id: Long,
    val category_id: Int,
    val category_title: String,
    val title_fa: String,
    val title_en: String,
    val rating: Rating?,
    val brand: Brand,
    val status: String,
    val images: Images,
    val default_variant_id: Long,
    val second_default_variant_id: Long,
    val parameters: Parameters,
    val product_type: String,
    val properties: Properties,
    val product_badges: List<ProductBadge>?,
    val price: Price,
    val digiplus: Digiplus?,
    val product_badge: ProductBadge2?
)
data class Rating(
    val rate: Double,
    val count: Double,
)

data class Brand(
    val id: Long,
    val title_fa: String,
    val title_en: String,
)

data class Images(
    val main: String,
    val image_list: List<String>,
)

data class Parameters(
    val color_ids: List<Long>,
)

data class Properties(
    val is_ready_to_ship: Boolean,
    val min_price_in_last_month: Double,
    val has_best_price: Boolean,
    val warehouse_label: String,
)

data class ProductBadge(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Double,
    val payload: Payload,
)

data class Payload(
    val svg_icon: String,
    val text: String,
    val text_color: String,
)

data class Price(
    val selling_price: Long,
    val rrp_price: Long,
    val order_limit: Int,
    val is_incredible: Boolean,
    val discount_percent: Int,
    val timer: String,
    val sold_percentage: Int,
    val badge: Badge,
)

data class Badge(
    val title: String,
    val color: String,
)

data class Digiplus(
    val services: List<String>,
    val services_summary: List<String>,
    val service_list: List<ServiceList>,
    val is_jet_eligible: Boolean,
    val fast_shipping_text: String,
)

data class ServiceList(
    val title: String,
    val badge: Badge2?,
)

data class Badge2(
    val title: String,
    val icon: String,
)

data class ProductBadge2(
    val text: String,
    val text_color: String,
    val icon: String,
    val icon_color: String,
    val image: String,
)

data class Category(
    val id: Long,
    val title_fa: String,
    val title_en: String,
    val return_reason_alert: String,
)

data class Variant(
    val id: Long,
    val rank: Double,
    val rate: Double,
    val statistics: Statistics,
    val status: String,
    val order_limit: Double,
    val properties: Any?,
    val digiplus: Digiplus2,
    val warranty: Warranty,
    val color: Color,
    val seller: Seller,
    val digiclub: Digiclub,
    val price: Price2,
    val shipment_methods: ShipmentMethods,
    val variant_badges: List<VariantBadge>,
)

data class Statistics(
    val totally_satisfied: Any?,
    val satisfied: Satisfied,
    val neutral: Any?,
    val dissatisfied: Dissatisfied,
    val totally_dissatisfied: Any?,
    val total_count: Double,
    val total_rate: Double,
)

data class Satisfied(
    val rate_count: Double,
    val rate: Double,
)

data class Dissatisfied(
    val rate_count: Double,
    val rate: Double,
)

data class Digiplus2(
    val services: List<String>,
    val services_summary: List<String>,
    val service_list: List<ServiceList2>,
    val is_jet_eligible: Boolean,
    val fast_shipping_text: String,
)

data class ServiceList2(
    val title: String,
    val badge: Badge3?,
)

data class Badge3(
    val title: String,
    val icon: String,
)

data class Warranty(
    val id: Long,
    val title_fa: String,
    val title_en: String,
)

data class Color(
    val id: Long,
    val title_fa: String,
    val title_en: String,
    val hex_code: String,
)

data class Seller(
    val id: Long,
    val title_fa: String,
    val title_en: String,
    val rating: Rating2,
    val properties: Any?,
    val stars: Double,
    val grade: Grade,
)

data class Rating2(
    val rate: Double,
    val count: Double,
)

data class Grade(
    val label: String,
    val color: String,
)

data class Digiclub(
    val point: Int,
)

data class Price2(
    val selling_price: Double,
    val rrp_price: Double,
    val order_limit: Double,
    val is_incredible: Boolean,
    val timer: String,
    val sold_percentage: Double,
    val badge: Badge4,
)

data class Badge4(
    val title: String,
    val color: String,
)

data class ShipmentMethods(
    val description: String,
    val icon: String,
    val icon_color: String,
    val providers: List<Provider>,
)

data class Provider(
    val title: String,
    val description: String,
    val type: String,
    val image: String,
)

data class VariantBadge(
    val id: Long,
    val type: String,
    val slot: String,
    val payload: Payload2,
)

data class Payload2(
    val text: String,
    val text_color: String,
    val svg_icon: String,
)

data class Review(
    val description: String?,
    val attributes: List<Attribute>,
)

data class Attribute(
    val title: String,
    val values: List<String>,
)

data class Breadcrumb(
    val id: Long,
    val title_fa: String,
    val title_en: String,
    val code: String,
    val top_product_image: String,
    val products_count: Double,
    val image: String?,
)

data class Video(
    val id: Double,
    val title: String,
    val url: String,
    val cover: String,
)

data class Comments(
    val count: Int,
    val latest_comments: List<LatestComment>,
)

data class LatestComment(
    val id: Long,
    val body: String,
    val rate: Double,
    val user: String,
    val user_name: String,
    val reactions: Reactions,
    val created_at: String,
    val is_buyer: Boolean,
    val title: String?,
    val is_recommended: String?,
)

data class Reactions(
    val likes: Double,
    val dislikes: Double,
)

data class Questions(
    val count: Int,
    val latest_questions: List<LatestQuestion>,
)

data class LatestQuestion(
    val text: String,
    val sender: String,
    val created_at: String,
    val last_answer: LastAnswer,
)

data class LastAnswer(
    val id: Long,
    val text: String,
    val sender: String,
    val marketplace_seller_id: Long?,
)

data class Meta(
    val share_url: String,
    val price_chart_url: String,
)

data class StCmpTacker(
    val neo: String,
    val cx: String,
    val dx: String,
    val data_fx: String,
    val zero: String,
)

data class BigdataTrackerData(
    val page_name: String,
    val page_info: PageInfo,
)

data class PageInfo(
    val product_id: Long,
)
