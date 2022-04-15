package com.rizkyhamdana.bumdesprototype.util

import androidx.recyclerview.widget.DiffUtil
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse

class ProductDiffutil(
    private val oldList: List<ProdukResponse>,
    private val newList : List<ProdukResponse>
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
            oldList[oldItemPosition].price != newList[newItemPosition].price -> {
                false
            }
            oldList[oldItemPosition].stand != newList[newItemPosition].stand -> {
                false
            }else -> true
        }
    }
}