package com.rizkyhamdana.bumdesprototype.ui.user.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.ProdukEntity
import com.rizkyhamdana.bumdesprototype.databinding.ListProductBinding

class ListProdukAdapter:
    RecyclerView.Adapter<ListProdukAdapter.ViewHolder>() {

    private var listProduk = ArrayList<ProdukEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProdukEntity)
    }

    fun setProduk(listProduk : List<ProdukEntity>){
        this.listProduk.clear()
        this.listProduk.addAll(listProduk)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(produkEntity: ProdukEntity){
            with(binding){
                tvNameProduk.text = produkEntity.name
                tvPrice.text = produkEntity.harga.toString()
                Glide.with(itemView.context)
                    .load(produkEntity.image)
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