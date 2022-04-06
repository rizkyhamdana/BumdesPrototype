package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularResponse(
    var name: String = " ",
    var price: Int = 0,
    var stand: String = " "
): Parcelable