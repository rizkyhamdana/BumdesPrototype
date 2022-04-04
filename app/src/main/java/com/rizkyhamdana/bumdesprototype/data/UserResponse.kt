package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserResponse(
    var email: String = " ",
    var address: String = " ",
    var name: String = " "
): Parcelable