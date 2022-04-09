package com.rizkyhamdana.bumdesprototype.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatResponse(
    var idSender: String = " ",
    var idReceiver: String = " ",
    var message: String = " ",
    var date : Long = 0
): Parcelable