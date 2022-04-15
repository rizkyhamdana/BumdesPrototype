package com.rizkyhamdana.bumdesprototype.ui.user.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OrderResponse
import com.rizkyhamdana.bumdesprototype.databinding.ListOrderBinding
import com.rizkyhamdana.bumdesprototype.util.OrderDiffutil

class HistoryAdapter:
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var listOrder = emptyList<OrderResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: OrderResponse)
    }

    fun setOrder(newList: List<OrderResponse>) {
        val diffutils = OrderDiffutil(listOrder, newList)
        val diffResult = DiffUtil.calculateDiff(diffutils)
        listOrder = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ListOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderEntity: OrderResponse) {
            with(binding) {
                tvNameProduk.text = orderEntity.details
                tvDate.text = orderEntity.date
                when (orderEntity.status) {
                    0 -> {
                        tvStatus.text = itemView.context.getString(R.string.menunggu)
                    }
                    1 -> {
                        tvStatus.text = itemView.context.getString(R.string.diproses)
                    }
                    else -> {
                        tvStatus.text = itemView.context.getString(R.string.selesai)
                    }
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = listOrder[position]
        holder.bind(order)
        holder.itemView.findViewById<TextView>(R.id.tv_detail).setOnClickListener {
            onItemClickCallback.onItemClicked(listOrder[position])
        }

    }

    override fun getItemCount(): Int = listOrder.size

}