package com.rizkyhamdana.bumdesprototype.ui.user.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.databinding.ListProductBinding
import com.rizkyhamdana.bumdesprototype.util.Const
import com.rizkyhamdana.bumdesprototype.util.ProductDiffutil

class ListProdukAdapter:
    RecyclerView.Adapter<ListProdukAdapter.ViewHolder>() {
    private var listProduk = emptyList<ProdukResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProdukResponse)
    }

    fun setProduk(newList: List<ProdukResponse>) {
        val diffutils = ProductDiffutil(listProduk, newList)
        val diffResult = DiffUtil.calculateDiff(diffutils)
        listProduk = newList
        diffResult.dispatchUpdatesTo(this)

    }

    inner class ViewHolder(private val binding: ListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(produkEntity: ProdukResponse) {
            with(binding) {
                tvNameProduk.text = produkEntity.name
                tvPrice.text =  Const.moneyNumber(produkEntity.price)
                Glide.with(itemView.context)
                    .load(
                        Const.FOOD_IMAGE
                    )
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
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listProduk[position])
        }

    }

    override fun getItemCount(): Int = listProduk.size

}