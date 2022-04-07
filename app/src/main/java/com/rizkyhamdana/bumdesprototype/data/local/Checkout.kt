package com.rizkyhamdana.bumdesprototype.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_checkout")
data class Checkout (
    @PrimaryKey
    val id: String,
    val idStand: String,
    val idUser: String,
    val name: String,
    val quantity: Int,
    val total: Int,
)