package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StandResponse(
    var id: String = " ",
    var name: String = " "
): Parcelable