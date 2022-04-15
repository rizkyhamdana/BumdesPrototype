package com.rizkyhamdana.bumdesprototype.ui.user.cart

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.local.Checkout
import com.rizkyhamdana.bumdesprototype.databinding.ActivityCartBinding
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity.Companion.EXTRA_DETAILS
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity.Companion.EXTRA_STAND
import com.rizkyhamdana.bumdesprototype.ui.user.checkout.CheckoutActivity.Companion.EXTRA_TOTAL
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailViewModel
import com.rizkyhamdana.bumdesprototype.util.Const.moneyNumber

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: DetailViewModel
    private lateinit var swipeToDeleteCallback: SwipeToDeleteCallback
    private lateinit var mAuth : FirebaseAuth
    private var totalBayar: Int = 0
    private var allOrder: String = " "
    private var stand: String = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        title =  "Keranjang"
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        adapter = CartAdapter()
        mAuth = FirebaseAuth.getInstance()
        drawLayout()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun isNetworkAvailable(): Boolean{
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
    }


    private fun drawLayout() {
        if (isNetworkAvailable()) {
            binding.cartActivity.visibility = View.VISIBLE
            binding.layoutNoInternet.root.visibility = View.GONE

            binding.rvCart.adapter = adapter
            binding.rvCart.layoutManager = LinearLayoutManager(this)

            val idUser = mAuth.currentUser?.uid as String
            viewModel.getCheckoutbyUser(idUser).observe(this){
                if (it.isEmpty()){
                    binding.cartActivity.visibility = View.GONE
                    binding.layoutNodata.root.visibility = View.VISIBLE
                }else{
                    adapter.setProduk(it)
                    totalBayar = 0
                    swipeToDeleteCallback = object : SwipeToDeleteCallback(){
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            val position = viewHolder.adapterPosition
                            viewModel.deleteCheckout(it[position])
                            binding.rvCart.adapter?.notifyItemRemoved(position)
                        }
                    }
                    val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
                    itemTouchHelper.attachToRecyclerView(binding.rvCart)
                    adapter.setOnItemClickCallback(object : CartAdapter
                    .OnItemClickCallback{
                        override fun onItemClicked(data: Checkout) {
                        }

                    })
                    for (i in it){
                        totalBayar += i.total
                        if(i == it.last()){
                            allOrder += "${i.name} ${i.quantity}x"
                            stand = i.idStand
                        }else{
                            allOrder += "${i.name} ${i.quantity}x, "
                        }
                    }
                    binding.tvTotal.text = moneyNumber(totalBayar)
                }
                viewModel.getUserbyId(idUser).observe(this){ user ->
                    binding.btnConfirmOrder.setOnClickListener {
                        if (totalBayar != 0){
                            val intent = Intent(this, CheckoutActivity::class.java)
                            intent.putExtra(CheckoutActivity.EXTRA_USER, user)
                            intent.putExtra(EXTRA_TOTAL, totalBayar)
                            intent.putExtra(EXTRA_DETAILS, allOrder)
                            intent.putExtra(EXTRA_STAND, stand)
                            startActivity(intent)
                        }
                    }
                }
            }
        }else{
            binding.cartActivity.visibility = View.GONE
            binding.layoutNoInternet.root.visibility = View.VISIBLE
            binding.layoutNoInternet.btnTryAgain.setOnClickListener {
                drawLayout()
            }
        }
    }
}