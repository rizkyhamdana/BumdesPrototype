package com.rizkyhamdana.bumdesprototype.ui.user.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.databinding.ListChatBinding
import com.rizkyhamdana.bumdesprototype.util.Const
import com.rizkyhamdana.bumdesprototype.util.UserMessageDiffutil

class MessageAdapter:
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var listOwner = emptyList<OwnerResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: OwnerResponse)
    }

    fun setList(newList: List<OwnerResponse>){
        val diffutils = UserMessageDiffutil(listOwner, newList)
        val diffResult = DiffUtil.calculateDiff(diffutils)
        listOwner = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ListChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ownerEntity: OwnerResponse) {
            with(binding) {
                tvName.text = ownerEntity.name
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
        val order = listOwner[position]
        holder.bind(order)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listOwner[position])
        }

    }

    override fun getItemCount(): Int = listOwner.size

}