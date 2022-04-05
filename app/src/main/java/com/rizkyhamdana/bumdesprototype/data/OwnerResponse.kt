package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerResponse(
    var email: String = " ",
    var address: String = " ",
    var number: String = " ",
    var stand: String = " ",
    var name: String = " "
): Parcelable