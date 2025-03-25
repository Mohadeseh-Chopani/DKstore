package com.example.digikala.data.models.home

//data class HomePageData(
//    val result: Result,
//    val status: Int
//)

data class HomePageData(
    val status: Long,
    val result: Result,
)

data class Result(
    val trending: Trending,
//    @JsonProperty("selling_and_sales")
    val selling_and_sales: SellingAndSales,
//    @JsonProperty("home_1")
    val home_1: Home1,
//    @JsonProperty("home_2")
    val home_2: Home2,
//    @JsonProperty("home_3")
    val home_3: Home3,
//    @JsonProperty("home_4")
    val home_4: Home4,
//    @JsonProperty("home_5")
    val home_5: Home5,
//    @JsonProperty("home_6")
    val home_6: Home6,
//    @JsonProperty("home_7")
    val home_7: Home7,
//    @JsonProperty("home_8")
    val home_8: Home8,
)

data class Trending(
    val code: String,
    val title: String,
    val products: List<Product>,
)

data class Product(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
    val rating: Rating?,
    val brand: Brand,
    val status: String,
    val images: Images,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val seconddefault_variant_id: Long,
    val parameters: Any?,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Properties,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge>,
    val price: Price,
    val digiplus: Any?,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge2,
//    @JsonProperty("warehouse_stock")
    val warehouse_stock: Long?,
//    @JsonProperty("title_en")
    val title_en: String?,
)

data class Rating(
    val rate: Double,
    val count: Long,
)

data class Brand(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images(
    val main: String,
)

data class Properties(
//    @JsonProperty("is_ready_to_ship")
    val is_ready_to_ship: Boolean?,
//    @JsonProperty("min_price_in_last_month")
    val min_priceIn_last_month: Long,
//    @JsonProperty("warehouse_label")
    val warehouse_label: String?,
//    @JsonProperty("has_best_price")
    val has_best_price: Boolean?,
//    @JsonProperty("is_ship_by_seller")
    val is_ship_by_seller: Boolean?,
)

data class ProductBadge(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload,
)

data class Payload(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_incredible")
    val is_incredible: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val timer: String?,
//    @JsonProperty("sold_percentage")
    val sold_percentage: Long?,
    val badge: Badge?,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
)

data class Badge(
    val title: String,
    val color: String,
)

data class ProductBadge2(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class SellingAndSales(
    val code: String,
    val title: String,
    val products: List<Product2>,
)

data class Product2(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
//    @JsonProperty("title_en")
    val title_en: String?,
    val rating: Any?,
    val brand: Brand2,
    val status: String,
    val images: Images2,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Any?,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Any?,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge3>?,
    val price: Price2,
    val digiplus: Digiplus,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge4?,
//    @JsonProperty("warehouse_stock")
    val warehouse_stock: Long?,
)

data class Brand2(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images2(
    val main: String,
)

data class ProductBadge3(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload2,
)

data class Payload2(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price2(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean,
//    @JsonProperty("discount_percent")
    val discount_percent: Long,
    val badge: Badge2,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
//    @JsonProperty("sold_percentage")
    val sold_percentage: Long?,
)

data class Badge2(
    val title: String,
    val color: String,
)

data class Digiplus(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList(
    val title: String,
    val badge: Badge3?,
)

data class Badge3(
    val title: String,
    val icon: String,
)

data class ProductBadge4(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class Home1(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product3>,
    val url: Url,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product3(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
//    @JsonProperty("title_en")
    val title_en: String,
    val rating: Rating?,
    val brand: Brand3,
    val status: String,
    val images: Images3,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Properties2,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge5>,
    val price: Price3,
    val digiplus: Digiplus2,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge6,
//    @JsonProperty("warehouse_stock")
    val warehouse_stock: Long?,
)

data class Rating2(
    val rate: Double?,
    val count: Long?,
)

data class Brand3(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images3(
    val main: String,
)

data class Parameters(
//    @JsonProperty("color_ids")
    val color_ids: List<Long>,
)

data class Properties2(
//    @JsonProperty("min_price_in_last_month")
    val min_priceIn_last_month: Long?,
//    @JsonProperty("is_ready_to_ship")
    val is_ready_to_ship: Boolean?,
//    @JsonProperty("has_best_price")
    val has_best_price: Boolean?,
//    @JsonProperty("warehouse_label")
    val warehouse_label: String?,
//    @JsonProperty("has_gift")
    val hasGift: Boolean?,
//    @JsonProperty("is_ship_by_seller")
    val is_ship_by_seller: Boolean?,
)

data class ProductBadge5(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload3,
)

data class Payload3(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price3(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
)

data class Digiplus2(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList2>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList2(
    val title: String,
    val badge: Badge4?,
)

data class Badge4(
    val title: String,
    val icon: String,
)

data class ProductBadge6(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class Url(
    val url: String,
    val page: String,
    val params: Params,
)

data class Params(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home2(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product4>,
    val url: Url2,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product4(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
    val rating: Rating?,
    val brand: Brand4,
    val status: String,
    val images: Images4,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters2,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Properties3,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge7>,
    val price: Price4,
    val digiplus: Digiplus3,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge8?,
)

data class Rating3(
    val rate: Double?,
    val count: Long?,
)

data class Brand4(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images4(
    val main: String,
)

data class Parameters2(
//    @JsonProperty("color_ids")
    val color_ids: List<Long>,
)

data class Properties3(
//    @JsonProperty("is_ship_by_seller")
    val is_ship_by_seller: Boolean?,
//    @JsonProperty("min_price_in_last_month")
    val min_priceIn_last_month: Long,
//    @JsonProperty("is_ready_to_ship")
    val is_ready_to_ship: Boolean?,
//    @JsonProperty("warehouse_label")
    val warehouse_label: String?,
)

data class ProductBadge7(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload4,
)

data class Payload4(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price4(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val badge: Badge5?,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
//    @JsonProperty("sold_percentage")
    val sold_percentage: Long?,
)

data class Badge5(
    val title: String,
    val color: String,
)

data class Digiplus3(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList3>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList3(
    val title: String,
    val badge: Badge6?,
)

data class Badge6(
    val title: String,
    val icon: String,
)

data class ProductBadge8(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class Url2(
    val url: String,
    val page: String,
    val params: Params2,
)

data class Params2(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home3(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product5>,
    val url: Url3,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product5(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
//    @JsonProperty("title_en")
    val title_en: String?,
    val rating: Any?,
    val brand: Brand5,
    val status: String,
    val images: Images5,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters3,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Any?,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge9>?,
    val price: Price5,
    val digiplus: Digiplus4,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge10?,
)

data class Brand5(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images5(
    val main: String,
)

data class Parameters3(
//    @JsonProperty("color_ids")
    val color_ids: List<Long>,
)

data class ProductBadge9(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload5,
)

data class Payload5(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price5(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val badge: Badge7?,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
)

data class Badge7(
    val title: String,
    val color: String,
)

data class Digiplus4(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList4>,
)

data class ServiceList4(
    val title: String,
    val badge: Badge8?,
)

data class Badge8(
    val title: String,
    val icon: String,
)

data class ProductBadge10(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
)

data class Url3(
    val url: String,
    val page: String,
    val params: Params3,
)

data class Params3(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home4(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product6>,
    val url: Url4,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product6(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
    val rating: Any?,
    val brand: Brand6,
    val status: String,
    val images: Images6,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters4,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Any?,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge11>,
    val price: Price6,
    val digiplus: Digiplus5,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge12?,
)

data class Brand6(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images6(
    val main: String,
)

data class Parameters4(
//    @JsonProperty("color_ids")
    val color_ids: List<Long>,
)

data class ProductBadge11(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload6,
)

data class Payload6(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price6(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val badge: Badge9?,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
//    @JsonProperty("sold_percentage")
    val sold_percentage: Long?,
)

data class Badge9(
    val title: String,
    val color: String,
)

data class Digiplus5(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList5>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList5(
    val title: String,
    val badge: Badge10?,
)

data class Badge10(
    val title: String,
    val icon: String,
)

data class ProductBadge12(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class Url4(
    val url: String,
    val page: String,
    val params: Params4,
)

data class Params4(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home5(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product7>,
    val url: Url5,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product7(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
    val rating: Any?,
    val brand: Brand7,
    val status: String,
    val images: Images7,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Any?,
    val price: Price7,
    val digiplus: Digiplus6,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge13?,
)

data class Brand7(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images7(
    val main: String,
)

data class Price7(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val badge: Badge11?,
)

data class Badge11(
    val title: String,
    val color: String,
)

data class Digiplus6(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList6>,
)

data class ServiceList6(
    val title: String,
    val badge: Badge12?,
)

data class Badge12(
    val title: String,
    val icon: String,
)

data class ProductBadge13(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
)

data class Url5(
    val url: String,
    val page: String,
    val params: Params5,
)

data class Params5(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home6(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product8>,
    val url: Url6,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product8(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
//    @JsonProperty("title_en")
    val title_en: String,
    val rating: Any?,
    val brand: Brand8,
    val status: String,
    val images: Images8,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters5,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Properties4,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge14>,
    val price: Price8,
    val digiplus: Digiplus7,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge15?,
//    @JsonProperty("warehouse_stock")
    val warehouse_stock: Long?,
//    @JsonProperty("product_class_badges")
    val product_class_badges: List<ProductClassBadge>?,
)

data class Brand8(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images8(
    val main: String,
)

data class Parameters5(
//    @JsonProperty("color_ids")
    val color_ids: List<Long>?,
)

data class Properties4(
//    @JsonProperty("is_ready_to_ship")
    val is_ready_to_ship: Boolean?,
//    @JsonProperty("warehouse_label")
    val warehouse_label: String?,
//    @JsonProperty("min_price_in_last_month")
    val min_priceIn_last_month: Long?,
//    @JsonProperty("is_ship_by_seller")
    val is_ship_by_seller: Boolean?,
)

data class ProductBadge14(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload7,
)

data class Payload7(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price8(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
//    @JsonProperty("sold_percentage")
    val sold_percentage: Long?,
    val badge: Badge13?,
)

data class Badge13(
    val title: String,
    val color: String,
)

data class Digiplus7(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList7>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList7(
    val title: String,
    val badge: Badge14?,
)

data class Badge14(
    val title: String,
    val icon: String,
)

data class ProductBadge15(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class ProductClassBadge(
    val icon: String,
    val title: String,
)

data class Url6(
    val url: String,
    val page: String,
    val params: Params6,
)

data class Params6(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home7(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product9>,
    val url: Url7,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product9(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
    val rating: Rating,
    val brand: Brand9,
    val status: String,
    val images: Images9,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Any?,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge16>,
    val price: Price9,
    val digiplus: Digiplus8,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge17?,
//    @JsonProperty("warehouse_stock")
    val warehouse_stock: Long?,
)

data class Rating4(
    val rate: Double,
    val count: Long,
)

data class Brand9(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images9(
    val main: String,
)

data class ProductBadge16(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload8,
)

data class Payload8(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Price9(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val badge: Badge15?,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
)

data class Badge15(
    val title: String,
    val color: String,
)

data class Digiplus8(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList8>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList8(
    val title: String,
    val badge: Badge16?,
)

data class Badge16(
    val title: String,
    val icon: String,
)

data class ProductBadge17(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class Url7(
    val url: String,
    val page: String,
    val params: Params7,
)

data class Params7(
//    @JsonProperty("widget_id")
    val widget_id: String,
)

data class Home8(
    val code: String,
//    @JsonProperty("recommendation_code")
    val recommendation_code: String,
    val title: String,
    val description: String,
    val products: List<Product10>,
    val url: Url8,
//    @JsonProperty("products_count")
    val products_count: Long,
)

data class Product10(
    val id: Long,
//    @JsonProperty("category_id")
    val category_id: Long,
//    @JsonProperty("category_title")
    val category_title: String,
//    @JsonProperty("title_fa")
    val title_fa: String,
    val rating: Rating,
    val brand: Brand10,
    val status: String,
    val images: Images10,
//    @JsonProperty("default_variant_id")
    val default_variant_id: Long,
//    @JsonProperty("second_default_variant_id")
    val second_default_variantId: Long,
    val parameters: Parameters,
//    @JsonProperty("product_type")
    val product_type: String,
    val properties: Properties5,
    val price: Price10,
    val digiplus: Digiplus9,
//    @JsonProperty("product_badges")
    val product_badges: List<ProductBadge18>?,
//    @JsonProperty("product_badge")
    val product_badge: ProductBadge19?,
)

data class Brand10(
//    @JsonProperty("title_fa")
    val title_fa: String,
)

data class Images10(
    val main: String,
)

data class Properties5(
//    @JsonProperty("min_price_in_last_month")
    val min_priceIn_last_month: Long,
//    @JsonProperty("has_best_price")
    val has_best_price: Boolean?,
//    @JsonProperty("is_ship_by_seller")
    val is_ship_by_seller: Boolean?,
//    @JsonProperty("is_ready_to_ship")
    val is_ready_to_ship: Boolean?,
//    @JsonProperty("warehouse_label")
    val warehouse_label: String?,
//    @JsonProperty("is_fake")
    val is_fake: Boolean?,
)

data class Price10(
//    @JsonProperty("selling_price")
    val selling_price: Long,
//    @JsonProperty("rrp_price")
    val rrp_price: Long,
//    @JsonProperty("order_limit")
    val order_limit: Long,
//    @JsonProperty("is_promotion")
    val is_promotion: Boolean?,
//    @JsonProperty("discount_percent")
    val discount_percent: Long?,
    val badge: Badge17?,
//    @JsonProperty("sold_percentage")
    val sold_percentage: Long?,
//    @JsonProperty("marketable_stock")
    val marketable_stock: Long?,
)

data class Badge17(
    val title: String,
    val color: String,
)

data class Digiplus9(
    val services: List<String>,
//    @JsonProperty("services_summary")
    val services_summary: List<String>,
//    @JsonProperty("service_list")
    val service_list: List<ServiceList9>,
//    @JsonProperty("is_jet_eligible")
    val is_jet_eligible: Boolean?,
//    @JsonProperty("fast_shipping_text")
    val fast_shipping_text: String?,
)

data class ServiceList9(
    val title: String,
    val badge: Badge18?,
)

data class Badge18(
    val title: String,
    val icon: String,
)

data class ProductBadge18(
    val id: Long,
    val type: String,
    val slot: String,
    val priority: Long,
    val payload: Payload9,
)

data class Payload9(
//    @JsonProperty("svg_icon")
    val svg_icon: String,
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
)

data class ProductBadge19(
    val text: String,
//    @JsonProperty("text_color")
    val text_color: String,
    val icon: String?,
//    @JsonProperty("icon_color")
    val icon_color: String?,
    val image: String?,
)

data class Url8(
    val url: String,
    val page: String,
    val params: Params8,
)

data class Params8(
//    @JsonProperty("widget_id")
    val widget_id: String,
)


data class MainBanner(
    val image_url: String
)
