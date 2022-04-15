package com.rizkyhamdana.bumdesprototype.util

import androidx.recyclerview.widget.DiffUtil
import com.rizkyhamdana.bumdesprototype.data.local.Checkout

class CartDiffutil(
    private val oldList: List<Checkout>,
    private val newList : List<Checkout>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].idStand != newList[newItemPosition].idStand -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].idUser != newList[newItemPosition].idUser -> {
                false
            }
            oldList[oldItemPosition].quantity != newList[newItemPosition].quantity -> {
                false
            }
            oldList[oldItemPosition].total != newList[newItemPosition].total -> {
                false
            }else -> true
        }
    }
}