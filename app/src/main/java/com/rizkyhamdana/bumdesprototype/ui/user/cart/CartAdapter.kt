package com.rizkyhamdana.bumdesprototype.ui.user.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.local.Checkout
import com.rizkyhamdana.bumdesprototype.databinding.ListCartBinding
import com.rizkyhamdana.bumdesprototype.util.Const

class CartAdapter:
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private var listProduk = ArrayList<Checkout>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Checkout)
    }

    fun setProduk(listProduk: List<Checkout>) {
        this.listProduk.clear()
        this.listProduk.addAll(listProduk)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(produkEntity: Checkout) {
            with(binding) {
                tvNameProduk.text = produkEntity.name
                tvPrice.text = "Rp. ${produkEntity.total}"
                tvQty.text = produkEntity.quantity.toString()
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
        val binding = ListCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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