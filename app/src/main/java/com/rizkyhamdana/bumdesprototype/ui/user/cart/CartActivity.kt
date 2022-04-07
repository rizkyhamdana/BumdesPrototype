package com.rizkyhamdana.bumdesprototype.ui.user.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.databinding.ActivityCartBinding
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: DetailViewModel
    private var totalBayar: Int = 0

    companion object{
        const val EXTRA_USER = "extra_user"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        title =  "Keranjang"
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        adapter = CartAdapter()
        val idUser = intent.getStringExtra(EXTRA_USER) as String
        binding.rvCart.adapter = adapter
        binding.rvCart.layoutManager = LinearLayoutManager(this)
        viewModel.getCheckoutbyUser(idUser).observe(this){
            adapter.setProduk(it)
            for (i in it){
                totalBayar += i.total
            }
            binding.tvTotal.text = "Rp $totalBayar"
        }

    }
}