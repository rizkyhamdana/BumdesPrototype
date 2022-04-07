package com.rizkyhamdana.bumdesprototype.ui.user.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.data.local.Checkout
import com.rizkyhamdana.bumdesprototype.databinding.ActivityDetailProductBinding
import com.rizkyhamdana.bumdesprototype.util.Const

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var viewModel: DetailViewModel
    private var qty: Int = 0
    private var isThisStand: Boolean = false


    companion object{
        const val EXTRA_PRODUCT = "extra_stand"
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        title =  "Detail"

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val data = intent.getParcelableExtra<ProdukResponse>(EXTRA_PRODUCT) as ProdukResponse
        val idUser = intent.getStringExtra(EXTRA_USER) as String
        setLayout(data)
        viewModel.getCheckoutbyId(data.id).observe(this){
            if (it !=  null){
                qty = it.quantity
            }
        }
        viewModel.getCheckoutbyUser(idUser).observe(this){
            if(it.isNotEmpty()){
                for (i in it){
                    if (i.idStand != data.idStand){
                        isThisStand = true
                        break
                    }
                }
            }
        }
        binding.btnAddToCart.setOnClickListener {
            val inputQty = binding.etQty.value
            if (inputQty>0){
                val qtyNow = qty + inputQty
                val subTotal = qtyNow * data.price
                val checkout = Checkout(
                    data.id,
                    data.idStand,
                    idUser,
                    data.name,
                    qtyNow,
                    subTotal
                )
                if (isThisStand){
                    viewModel.clearCheckout()
                    viewModel.insertCheckout(checkout)
                    Toast.makeText(this, "Berhasil memasukkan barang di keranjang dan menghapus keranjang dari kedai lain", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.insertCheckout(checkout)
                    Toast.makeText(this, "Berhasil memasukkan barang di keranjang", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun setLayout(data: ProdukResponse) {
        binding.apply {
            tvNameProduk.text = data.name
            tvPrice.text = "Rp ${data.price}"
            tvStandName.text = data.stand
            Glide.with(this@DetailProductActivity)
                .load(
                    Const.FOOD_IMAGE
                )
                .apply(RequestOptions())
                .into(imgProduk)
        }
    }
}