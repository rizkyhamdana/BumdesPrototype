package com.rizkyhamdana.bumdesprototype.ui.owner.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OrderResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityDetailHistoryBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity
import com.rizkyhamdana.bumdesprototype.util.Const

class DetailOwnerHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    companion object{
        const val EXTRA_HISTORY = "extra_history"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        title =  "Detail Pesanan"
        val data = intent.getParcelableExtra<OrderResponse>(EXTRA_HISTORY) as OrderResponse
        val status = data.status
        setLayout(data, status)
        val updateOrder = mapOf(
            "id" to data.id,
            "name" to data.name,
            "address" to data.address,
            "number" to data.number,
            "idStand" to data.idStand,
            "idUser" to data.idUser,
            "details" to data.details,
            "date" to data.date,
            "status" to 1,
            "pay" to data.pay,
            "methodOrder" to data.methodOrder
        )
        binding.btnProcess.setOnClickListener {
            val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
            firebaseDb.getReference("order").child(data.id).updateChildren(updateOrder).addOnSuccessListener {
                startActivity(Intent(this, OwnerActivity::class.java))
                Toast.makeText(this, "Pesanan berhasil diproses", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setLayout(data: OrderResponse, status: Int) {
        binding.apply {
            tvName.text = data.name
            tvNumber.text = data.number
            tvAddress.text = data.address
            tvPesanan.text = data.details
            tvDate.text = data.date
            tvMetode.text = data.methodOrder
            tvBayar.text = Const.moneyNumber(data.pay)
        }
        when (status) {
            0 -> {
                binding.btnProcess.text = getString(R.string.process_order)
                binding.btnProcess.visibility = View.VISIBLE
                binding.tvStatus.text = getString(R.string.menunggu)
            }
            1 -> {
                binding.btnProcess.visibility = View.GONE
                binding.tvStatus.text = getString(R.string.diproses)
            }
            else -> {
                binding.btnProcess.visibility = View.GONE
                binding.tvStatus.text = getString(R.string.selesai)
            }
        }
    }
}