package com.rizkyhamdana.bumdesprototype.ui.user.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.StandResponse
import com.rizkyhamdana.bumdesprototype.databinding.ListShopBinding
import com.rizkyhamdana.bumdesprototype.util.Const.STAND_IMAGE

class ListKedaiAdapter:
    RecyclerView.Adapter<ListKedaiAdapter.ViewHolder>() {

    private var listKedai = ArrayList<StandResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: StandResponse)
    }

    fun setKedai(listKedai: List<StandResponse>){
        this.listKedai.clear()
        this.listKedai.addAll(listKedai)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListShopBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(kedaiEntity: StandResponse){
            with(binding){
                tvName.text = kedaiEntity.name
                Glide.with(itemView.context)
                    .load(
                        STAND_IMAGE)
                    .apply(RequestOptions())
                    .into(imgShop)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kedai = listKedai[position]
        holder.bind(kedai)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listKedai[position])
        }

    }

    override fun getItemCount(): Int = listKedai.size

}