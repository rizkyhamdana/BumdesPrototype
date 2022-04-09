package com.rizkyhamdana.bumdesprototype.ui.user.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.data.local.Checkout
import com.rizkyhamdana.bumdesprototype.databinding.ActivityCartBinding
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity.Companion.EXTRA_DETAILS
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity.Companion.EXTRA_STAND
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity.Companion.EXTRA_TOTAL
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: DetailViewModel
    private var totalBayar: Int = 0
    private var allOrder: String = " "
    private var stand: String = " "

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
        val user = intent.getParcelableExtra<UserResponse>(EXTRA_USER) as UserResponse
        binding.rvCart.adapter = adapter
        binding.rvCart.layoutManager = LinearLayoutManager(this)
        viewModel.getCheckoutbyUser(user.id).observe(this){
            adapter.setProduk(it)
            for (i in it){
                totalBayar += i.total
                if(i == it.last()){
                    allOrder += "${i.name} ${i.quantity}x"
                    stand = i.idStand
                }else{
                    allOrder += "${i.name} ${i.quantity}x, "
                }
            }
            binding.tvTotal.text = "Rp $totalBayar"
        }
        adapter.setOnItemClickCallback(object : CartAdapter
        .OnItemClickCallback{
            override fun onItemClicked(data: Checkout) {
            }

        })
        binding.btnConfirmOrder.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra(CheckoutActivity.EXTRA_USER, user)
            intent.putExtra(EXTRA_TOTAL, totalBayar)
            intent.putExtra(EXTRA_DETAILS, allOrder)
            intent.putExtra(EXTRA_STAND, stand)
            startActivity(intent)
        }

    }
}