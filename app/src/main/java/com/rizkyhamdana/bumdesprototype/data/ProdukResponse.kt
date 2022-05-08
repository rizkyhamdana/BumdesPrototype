package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProdukResponse(
    var name: String = " ",
    var idStand: String = " ",
    var imageUrl: String = " ",
    var id: String = " ",
    var stand: String = " ",
    var price: Int = 0,
): Parcelable