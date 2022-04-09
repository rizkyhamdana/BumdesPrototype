package com.rizkyhamdana.bumdesprototype.ui.user.checkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OrderResponse
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityCheckoutBinding
import com.rizkyhamdana.bumdesprototype.ui.user.MainActivity
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailViewModel
import com.rizkyhamdana.bumdesprototype.util.Const
import java.text.SimpleDateFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var viewModel : DetailViewModel
    private var radioString: String = " "

    companion object{
        const val EXTRA_USER = "extra_user"
        const val EXTRA_TOTAL = "extra_total"
        const val EXTRA_DETAILS = "extra_details"
        const val EXTRA_STAND = "extra_stand"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        title =  "Checkout"
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val user = intent.getParcelableExtra<UserResponse>(EXTRA_USER) as UserResponse
        val totalBayar = intent.getIntExtra(EXTRA_TOTAL, 0)
        val stand = intent.getStringExtra(EXTRA_STAND) as String
        val details = intent.getStringExtra(EXTRA_DETAILS) as String

        binding.apply {
            tvName.text = user.name
            tvAddress.text = user.address
            tvNumber.text = user.number
            tvTotal.text = "Rp $totalBayar"
        }
        binding.btnConfirmOrder.setOnClickListener {
            val id = binding.radioGroup.checkedRadioButtonId
            if (id!=-1){
                radioString = if (id == R.id.rb_delivery){
                    "Pesan antar"
                }else{
                    "Ambil di tempat"
                }
                sendOrder(user, totalBayar, stand, details, radioString)
                viewModel.clearCheckout()
                Toast.makeText(this, "Pesanan berhasil diproses", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Pilih metode pemesanan terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun sendOrder(user: UserResponse, totalBayar: Int, stand: String, details: String, radioString: String) {
        val sdf = SimpleDateFormat("dd/MM/yyyy, HH:mm")
        val currentDate = sdf.format(Date())
        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
        val reference = firebaseDb.getReference("order")
        val id = reference.push().key as String
        val model = OrderResponse(
            id,
            user.name,
            user.address,
            user.number,
            stand,
            user.id,
            details,
            currentDate,
            0,
            totalBayar,
            radioString
        )
        reference.child(id).setValue(model)
    }


}