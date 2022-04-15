package com.rizkyhamdana.bumdesprototype.ui.owner.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.databinding.ListChatBinding
import com.rizkyhamdana.bumdesprototype.util.Const
import com.rizkyhamdana.bumdesprototype.util.OwnerMessageDiffutil

class OwnerMessageAdapter:
    RecyclerView.Adapter<OwnerMessageAdapter.ViewHolder>() {

    private var listUser = emptyList<UserResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserResponse)
    }

    fun setList(newList: List<UserResponse>) {
        val diffutils = OwnerMessageDiffutil(listUser, newList)
        val diffResult = DiffUtil.calculateDiff(diffutils)
        listUser= newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ListChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userEntity: UserResponse) {
            with(binding) {
                tvName.text = userEntity.name
                Glide.with(itemView.context)
                    .load(Const.PROFILE_IMAGE)
                    .apply(RequestOptions())
                    .into(imgUser)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = listUser[position]
        holder.bind(order)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[position])
        }

    }

    override fun getItemCount(): Int = listUser.size

}