package com.example.digikala.utils

import android.util.Log
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
            val tomanPrice = price?.substring(0, price.length -1)?.toInt()
            return NumberFormat.getNumberInstance(Locale.US).format(tomanPrice)
        }
    }
}