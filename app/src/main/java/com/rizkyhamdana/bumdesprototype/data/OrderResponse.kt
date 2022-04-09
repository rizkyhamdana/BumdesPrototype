package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderResponse(
    var id: String = " ",
    var name: String = " ",
    var address: String = " ",
    var number: String = " ",
    var idStand: String = " ",
    var idUser: String = " ",
    var details: String = " ",
    var date: String = " ",
    var status: Int = 0,
    var pay: Int = 0,
    var methodOrder: String = " "
): Parcelable