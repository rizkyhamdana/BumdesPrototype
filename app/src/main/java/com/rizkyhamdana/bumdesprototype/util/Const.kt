package com.rizkyhamdana.bumdesprototype.util

import java.text.NumberFormat
import java.util.*

object Const {

    const val BASE_URL = "https://e-cooliner-default-rtdb.asia-southeast1.firebasedatabase.app"
    const val MILLIS = 3000L
    const val STAND_IMAGE = "file:///android_asset/stand_placeholder.jpg"
    const val FOOD_IMAGE = "file:///android_asset/food_placeholder.jpg"
    const val PROFILE_IMAGE = "file:///android_asset/profile_placeholder.jpg"

    fun moneyNumber(money: Int): String{
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(money)
    }

}