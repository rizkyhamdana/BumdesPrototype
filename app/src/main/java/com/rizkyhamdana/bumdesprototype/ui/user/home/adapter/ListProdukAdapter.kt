package com.rizkyhamdana.bumdesprototype.ui.user.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.PopularResponse
import com.rizkyhamdana.bumdesprototype.data.ProdukEntity
import com.rizkyhamdana.bumdesprototype.databinding.ListProductBinding

class ListProdukAdapter:
    RecyclerView.Adapter<ListProdukAdapter.ViewHolder>() {

    private var listProduk = ArrayList<PopularResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PopularResponse)
    }

    fun setProduk(listProduk : List<PopularResponse>){
        this.listProduk.clear()
        this.listProduk.addAll(listProduk)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(produkEntity: PopularResponse){
            with(binding){
                tvNameProduk.text = produkEntity.name
                tvPrice.text = "Rp. ${produkEntity.price}"
                Glide.with(itemView.context)
                    .load(
                        "file:///android_asset/placeholder_produk.png")
                    .apply(RequestOptions())
                    .into(imgProduk)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.bind(produk)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listProduk[position])
        }

    }

    override fun getItemCount(): Int = listProduk.size

}