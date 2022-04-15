package com.rizkyhamdana.bumdesprototype.util

import androidx.recyclerview.widget.DiffUtil
import com.rizkyhamdana.bumdesprototype.data.UserResponse

class OwnerMessageDiffutil(
    private val oldList: List<UserResponse>,
    private val newList : List<UserResponse>
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
            oldList[oldItemPosition].email != newList[newItemPosition].email -> {
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
            }else -> true
        }
    }
}