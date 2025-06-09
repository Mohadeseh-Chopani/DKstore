package com.example.digikala.data.models.search

data class SearchData(
    val status: Int,
    val result: Result
)

data class Result(
    val filters: Filters,
    val sort: Sort,
    val related_search_words: List<String>,
    val meta: Meta,
    val search_phase: Int,
    val pager: Pager,
    val search_version: String,
    val text_lenz_eligibility: String,
    val advertisement: Advertisement,
    val products: List<ProductsItem>
)

data class Filters(
    val categories: FiltersCategories,
    val brands: FiltersBrands,
    val price: FiltersPrice,
    val seller_types: FiltersSeller_types,
    val has_selling_stock: FiltersHas_selling_stock,
    val has_ready_to_shipment: FiltersHas_ready_to_shipment,
    val digiplus: FiltersDigiplus,
    val has_jet_delivery: FiltersHas_jet_delivery,
    val only_fresh: FiltersOnly_fresh,
    val has_ship_by_seller: FiltersHas_ship_by_seller
)

data class FiltersCategories(
    val title: String,
    val options: List<FiltersCategoriesOptionsItem>,
    val type: String
)

data class FiltersCategoriesOptionsItem(
    val id: Int,
    val title_fa: String,
    val title_en: String
)

data class FiltersBrands(
    val title: String,
    val options: List<BrandsOptionsItem>,
    val type: String
)

data class BrandsOptionsItem(
    val id: Int,
    val title_fa: String,
    val title_en: String
)

data class FiltersPrice(
    val title: String,
    val options: FiltersPriceOptions,
    val type: String
)

data class FiltersPriceOptions(
    val max: Long
)

data class FiltersSeller_types(
    val title: String,
    val options: List<FiltersSeller_typesOptionsItem>,
    val type: String
)

data class FiltersSeller_typesOptionsItem(
    val id: String,
    val title_fa: String,
    val title_en: String
)

data class FiltersHas_selling_stock(
    val title: String,
    val options: List<FiltersHas_selling_stockOptionsItem>,
    val type: String
)

data class FiltersHas_selling_stockOptionsItem(
    val title_fa: String,
    val title_en: String
)

data class FiltersHas_ready_to_shipment(
    val title: String,
    val options: List<FiltersHas_ready_to_shipmentOptionsItem>,
    val type: String
)

data class FiltersHas_ready_to_shipmentOptionsItem(
    val title_fa: String,
    val title_en: String
)

data class FiltersDigiplus(
    val title: String,
    val icon: String,
    val icon_color: String,
    val options: List<FiltersDigiplusOptionsItem>,
    val type: String
)

data class FiltersDigiplusOptionsItem(
    val id: String,
    val title_fa: String,
    val title_en: String,
    val icon: String,
    val icon_color: String
)

data class FiltersHas_jet_delivery(
    val title: String,
    val icon: String,
    val icon_color: String,
    val type: String
)

data class FiltersOnly_fresh(
    val title: String,
    val icon: String,
    val icon_color: String,
    val options: List<FiltersOnly_freshOptionsItem>,
    val type: String
)

data class FiltersOnly_freshOptionsItem(
    val title_fa: String,
    val title_en: String
)

data class FiltersHas_ship_by_seller(
    val title: String,
    val icon: String,
    val icon_color: String,
    val options: List<FiltersHas_ship_by_sellerOptionsItem>,
    val type: String,
    val description: String
)

data class FiltersHas_ship_by_sellerOptionsItem(
    val title_fa: String,
    val title_en: String
)

data class Sort(
    val default: Int
)

data class Meta(
    val share_url: String
)

data class Pager(
    val current_page: Int,
    val total_pages: Int,
    val total_items: Int
)

data class Advertisement(
    val sponsored_brands: AdvertisementSponsored_brands
)

data class AdvertisementSponsored_brands(
    val brand: AdvertisementSponsored_brandsBrand,
    val products: List<AdvertisementSponsored_brandsProductsItem>,
    val campaign_id: Int
)

data class AdvertisementSponsored_brandsBrand(
    val id: Int,
    val title_fa: String,
    val title_en: String,
    val image: String
)

data class AdvertisementSponsored_brandsProductsItem(
    val id: Int,
    val category_id: Int,
    val category_title: String,
    val title_fa: String,
    val title_en: String,
    val rating: Any?,
    val brand: AdvertisementSponsored_brandsProductsItemBrand,
    val status: String,
    val images: AdvertisementSponsored_brandsProductsItemImages,
    val default_variant_id: Int,
    val second_default_variant_id: Int,
    val parameters: AdvertisementSponsored_brandsProductsItemParameters,
    val warehouse_stock: Int?,
    val product_type: String,
    val properties: AdvertisementSponsored_brandsProductsItemProperties,
    val product_badges: List<AdvertisementSponsored_brandsProductsItemProduct_badgesItem>,
    val price: AdvertisementSponsored_brandsProductsItemPrice,
    val variants: List<AdvertisementSponsored_brandsProductsItemVariantsItem>,
    val digiplus: AdvertisementSponsored_brandsProductsItemDigiplus,
    val product_badge: AdvertisementSponsored_brandsProductsItemProduct_badge
)

data class AdvertisementSponsored_brandsProductsItemBrand(
    val title_fa: String
)

data class AdvertisementSponsored_brandsProductsItemImages(
    val main: String
)

data class AdvertisementSponsored_brandsProductsItemParameters(
    val color_ids: List<Int>
)

data class AdvertisementSponsored_brandsProductsItemProperties(
    val is_ready_to_ship: Boolean,
    val min_price_in_last_month: Int,
    val warehouse_label: String
)

data class AdvertisementSponsored_brandsProductsItemProduct_badgesItem(
    val id: Int,
    val type: String,
    val slot: String,
    val priority: Int,
    val payload: AdvertisementSponsored_brandsProductsItemProduct_badgesItemPayload
)

data class AdvertisementSponsored_brandsProductsItemProduct_badgesItemPayload(
    val svg_icon: String,
    val text: String,
    val text_color: String
)

data class AdvertisementSponsored_brandsProductsItemPrice(
    val selling_price: Int,
    val rrp_price: Int,
    val order_limit: Int,
    val is_promotion: Boolean,
    val discount_percent: Int,
    val marketable_stock: Int,
    val badge: AdvertisementSponsored_brandsProductsItemPriceBadge
)

data class AdvertisementSponsored_brandsProductsItemPriceBadge(
    val title: String,
    val color: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItem(
    val id: Int,
    val rank: Double,
    val status: String,
    val order_limit: Int,
    val properties: Any?,
    val digiplus: AdvertisementSponsored_brandsProductsItemVariantsItemDigiplus,
    val warranty: AdvertisementSponsored_brandsProductsItemVariantsItemWarranty,
    val color: AdvertisementSponsored_brandsProductsItemVariantsItemColor,
    val seller: AdvertisementSponsored_brandsProductsItemVariantsItemSeller,
    val digiclub: AdvertisementSponsored_brandsProductsItemVariantsItemDigiclub,
    val insurance: AdvertisementSponsored_brandsProductsItemVariantsItemInsurance,
    val price: AdvertisementSponsored_brandsProductsItemVariantsItemPrice,
    val shipment_methods: AdvertisementSponsored_brandsProductsItemVariantsItemShipment_methods,
    val variant_badges: List<AdvertisementSponsored_brandsProductsItemVariantsItemVariant_badgesItem>
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemDigiplus(
    val services: List<String>,
    val service_list: List<AdvertisementSponsored_brandsProductsItemVariantsItemDigiplusService_listItem>,
    val services_summary: List<String>,
    val is_jet_eligible: Boolean,
    val fast_shipping_text: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemDigiplusService_listItem(
    val title: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemWarranty(
    val id: Int,
    val title_fa: String,
    val title_en: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemColor(
    val id: Int,
    val title_fa: String,
    val title_en: String,
    val hex_code: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemSeller(
    val id: Int,
    val title_fa: String,
    val title_en: String,
    val rating: Any?,
    val properties: Any?,
    val stars: Double,
    val grade: AdvertisementSponsored_brandsProductsItemVariantsItemSellerGrade
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemSellerGrade(
    val label: String,
    val color: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemDigiclub(
    val point: Int
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemInsurance(
    val title: String,
    val subtitle: String,
    val description: String,
    val covers: List<AdvertisementSponsored_brandsProductsItemVariantsItemInsuranceCoversItem>,
    val base_premium: Int,
    val tax: Int,
    val total_premium: Int,
    val terms_and_conditions: String,
    val before_discount: Int,
    val discount_percent: Int
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemInsuranceCoversItem(
    val description: String,
    val max_use_count: Int
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemPrice(
    val selling_price: Int,
    val rrp_price: Int,
    val order_limit: Int,
    val is_promotion: Boolean,
    val marketable_stock: Int,
    val badge: AdvertisementSponsored_brandsProductsItemVariantsItemPriceBadge
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemPriceBadge(
    val title: String,
    val color: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemShipment_methods(
    val description: String,
    val icon: String,
    val icon_color: String,
    val providers: List<AdvertisementSponsored_brandsProductsItemVariantsItemShipment_methodsProvidersItem>
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemShipment_methodsProvidersItem(
    val title: String,
    val description: String,
    val type: String,
    val image: String
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemVariant_badgesItem(
    val id: Int,
    val type: String,
    val slot: String,
    val payload: AdvertisementSponsored_brandsProductsItemVariantsItemVariant_badgesItemPayload
)

data class AdvertisementSponsored_brandsProductsItemVariantsItemVariant_badgesItemPayload(
    val text: String,
    val text_color: String,
    val svg_icon: String
)

data class AdvertisementSponsored_brandsProductsItemDigiplus(
    val services: List<String>,
    val service_list: List<AdvertisementSponsored_brandsProductsItemDigiplusService_listItem>,
    val services_summary: List<String>,
    val is_jet_eligible: Boolean,
    val fast_shipping_text: String
)

data class AdvertisementSponsored_brandsProductsItemDigiplusService_listItem(
    val title: String
)

data class AdvertisementSponsored_brandsProductsItemProduct_badge(
    val text: String,
    val text_color: String
)

data class ProductsItem(
    val platforms: List<String>,
    val default_variant: ProductsItemDefault_variant,
    val id: Int,
    val category_id: Int,
    val category_title: String,
    val title_fa: String,
    val title_en: String,
    val rating: ProductsItemRating,
    val brand: ProductsItemBrand,
    val status: String,
    val images: ProductsItemImages,
    val default_variant_id: Int,
    val second_default_variant_id: Int,
    val parameters: ProductsItemParameters,
    val product_type: String,
    val properties: ProductsItemProperties,
    val product_badges: List<ProductsItemProduct_badgesItem>,
    val price: ProductsItemPrice,
    val digiplus: ProductsItemDigiplus,
    val product_badge: ProductsItemProduct_badge
)

data class ProductsItemDefault_variant(
    val id: Int,
    val rank: Double,
    val rate: Double,
    val statistics: ProductsItemDefault_variantStatistics,
    val status: String,
    val order_limit: Int,
    val properties: Any?,
    val digiplus: ProductsItemDefault_variantDigiplus,
    val warranty: ProductsItemDefault_variantWarranty,
    val color: ProductsItemDefault_variantColor,
    val seller: ProductsItemDefault_variantSeller,
    val digiclub: ProductsItemDefault_variantDigiclub,
    val insurance: ProductsItemDefault_variantInsurance,
    val price: ProductsItemDefault_variantPrice,
    val shipment_methods: ProductsItemDefault_variantShipment_methods
)

data class ProductsItemDefault_variantStatistics(
    val totally_satisfied: ProductsItemDefault_variantStatisticsTotally_satisfied,
    val satisfied: ProductsItemDefault_variantStatisticsSatisfied,
    val neutral: ProductsItemDefault_variantStatisticsNeutral,
    val dissatisfied: ProductsItemDefault_variantStatisticsDissatisfied,
    val totally_dissatisfied: ProductsItemDefault_variantStatisticsTotally_dissatisfied,
    val total_count: Double,
    val total_rate: Double
)

data class ProductsItemDefault_variantStatisticsTotally_satisfied(
    val rate_count: Double,
    val rate: Double
)

data class ProductsItemDefault_variantStatisticsSatisfied(
    val rate_count: Double,
    val rate: Double
)

data class ProductsItemDefault_variantStatisticsNeutral(
    val rate_count: Double,
    val rate: Double
)

data class ProductsItemDefault_variantStatisticsDissatisfied(
    val rate_count: Double,
    val rate: Double
)

data class ProductsItemDefault_variantStatisticsTotally_dissatisfied(
    val rate_count: Double,
    val rate: Double
)

data class ProductsItemDefault_variantDigiplus(
    val services: List<String>,
    val service_list: List<ProductsItemDefault_variantDigiplusService_listItem>,
    val services_summary: List<String>,
    val is_jet_eligible: Boolean,
    val fast_shipping_text: String
)

data class ProductsItemDefault_variantDigiplusService_listItem(
    val title: String
)

data class ProductsItemDefault_variantWarranty(
    val id: Int,
    val title_fa: String,
    val title_en: String
)

data class ProductsItemDefault_variantColor(
    val id: Int,
    val title_fa: String,
    val title_en: String,
    val hex_code: String
)

data class ProductsItemDefault_variantSeller(
    val id: Int,
    val title_fa: String,
    val title_en: String,
    val rating: Any?,
    val properties: Any?,
    val stars: Double,
    val grade: ProductsItemDefault_variantSellerGrade
)

data class ProductsItemDefault_variantSellerGrade(
    val label: String,
    val color: String
)

data class ProductsItemDefault_variantDigiclub(
    val point: Int
)

data class ProductsItemDefault_variantInsurance(
    val title: String,
    val subtitle: String,
    val description: String,
    val covers: List<ProductsItemDefault_variantInsuranceCoversItem>,
    val base_premium: Int,
    val tax: Int,
    val total_premium: Int,
    val terms_and_conditions: String,
    val before_discount: Int,
    val discount_percent: Int
)

data class ProductsItemDefault_variantInsuranceCoversItem(
    val description: String,
    val max_use_count: Int
)

data class ProductsItemDefault_variantPrice(
    val selling_price: Int,
    val rrp_price: Int,
    val order_limit: Int,
    val is_promotion: Boolean,
    val sold_percentage: Int,
    val badge: ProductsItemDefault_variantPriceBadge
)

data class ProductsItemDefault_variantPriceBadge(
    val title: String,
    val color: String
)

data class ProductsItemDefault_variantShipment_methods(
    val description: String,
    val icon: String,
    val icon_color: String,
    val providers: List<ProductsItemDefault_variantShipment_methodsProvidersItem>
)

data class ProductsItemDefault_variantShipment_methodsProvidersItem(
    val title: String,
    val description: String,
    val type: String,
    val image: String
)

data class ProductsItemRating(
    val rate: Double,
    val count: Int
)

data class ProductsItemBrand(
    val title_fa: String
)

data class ProductsItemImages(
    val main: String
)

data class ProductsItemParameters(
    val color_ids: List<Int>
)

data class ProductsItemProperties(
    val is_ready_to_ship: Boolean,
    val min_price_in_last_month: Int,
    val has_best_price: Boolean,
    val warehouse_label: String
)

data class ProductsItemProduct_badgesItem(
    val id: Int,
    val type: String,
    val slot: String,
    val priority: Int,
    val payload: ProductsItemProduct_badgesItemPayload
)

data class ProductsItemProduct_badgesItemPayload(
    val svg_icon: String,
    val text: String,
    val text_color: String
)

data class ProductsItemPrice(
    val selling_price: Int,
    val rrp_price: Int,
    val order_limit: Int,
    val discount_percent: Int
)

data class ProductsItemDigiplus(
    val services: List<String>,
    val service_list: List<ProductsItemDigiplusService_listItem>,
    val services_summary: List<String>,
    val is_jet_eligible: Boolean,
    val fast_shipping_text: String
)

data class ProductsItemDigiplusService_listItem(
    val title: String
)

data class ProductsItemProduct_badge(
    val text: String,
    val text_color: String,
    val icon: String,
    val icon_color: String,
    val image: String
)