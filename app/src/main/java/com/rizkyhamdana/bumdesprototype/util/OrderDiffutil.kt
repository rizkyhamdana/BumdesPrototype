package com.rizkyhamdana.bumdesprototype.util

import androidx.recyclerview.widget.DiffUtil
import com.rizkyhamdana.bumdesprototype.data.OrderResponse

class OrderDiffutil(
    private val oldList: List<OrderResponse>,
    private val newList : List<OrderResponse>
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
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].address != newList[newItemPosition].address -> {
                false
            }
            oldList[oldItemPosition].number != newList[newItemPosition].number -> {
                false
            }
            oldList[oldItemPosition].idStand != newList[newItemPosition].idStand -> {
                false
            }
            oldList[oldItemPosition].idUser != newList[newItemPosition].idUser -> {
                false
            }
            oldList[oldItemPosition].details != newList[newItemPosition].details -> {
                false
            }
            oldList[oldItemPosition].date != newList[newItemPosition].date -> {
                false
            }
            oldList[oldItemPosition].status != newList[newItemPosition].status -> {
                false
            }
            oldList[oldItemPosition].methodOrder != newList[newItemPosition].methodOrder -> {
                false
            }
            oldList[oldItemPosition].pay != newList[newItemPosition].pay -> {
                false
            }else -> true
        }
    }
}