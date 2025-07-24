package com.example.digikala.utils

import android.util.Log
import com.example.digikala.data.dataSource.local.ShoppingCardEntity
import com.example.digikala.data.models.product.ShoppingCardPrices
import java.text.NumberFormat
import java.util.Locale

class ConvertNumbers {
    companion object {
        fun convertToPersianDigits(number: String): String {
            val persianDigits = arrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
            val builder = StringBuilder()
            number.forEach { char ->
                if (char.isDigit()) {
                    builder.append(persianDigits[char.toString().toInt()])
                } else {
                    builder.append(char)
                }
            }
            return builder.toString()
        }

        fun convertRialToToman(price: String?): String {
            return price?.takeIf { it.length > 1 }?.let {
                runCatching {
                    val tomanPrice = it.substring(0, it.length - 1).toInt()
                    NumberFormat.getNumberInstance(Locale.US).format(tomanPrice)
                }.getOrElse { "0" }
            } ?: "0"
        }

        fun calculatePrices(data: List<ShoppingCardEntity>): ShoppingCardPrices {
            var totalPriceWithProfit: Long = 0
            var totalTakhfif: Long = 0
            var totalPrice: Long = 0

            for (item in data) {
                totalTakhfif += (item.products.price.rrp_price - item.products.price.selling_price)
                totalPrice += item.products.price.rrp_price

            }
            totalPriceWithProfit = totalPrice - totalTakhfif

            val shoppingCardPrices = ShoppingCardPrices(
                totalPrice,
                totalPriceWithProfit,
                totalTakhfif
            )

            return shoppingCardPrices
        }
    }
}