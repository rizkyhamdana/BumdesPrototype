package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserResponse(
    var email: String = " ",
    var id: String = " ",
    var address: String = " ",
    var number: String = " ",
    var name: String = " "
): Parcelable