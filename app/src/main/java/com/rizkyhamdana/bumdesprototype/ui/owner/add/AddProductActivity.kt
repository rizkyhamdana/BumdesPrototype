package com.rizkyhamdana.bumdesprototype.ui.owner.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityAddProductBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity
import com.rizkyhamdana.bumdesprototype.util.Const

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private var radioString: String = " "

    companion object{
        const val EXTRA_OWNER = "extra_owner"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolBar)
        title = "Tambah Produk"

        val data = intent.getParcelableExtra<OwnerResponse>(EXTRA_OWNER) as OwnerResponse

        binding.btnAdd.setOnClickListener {
            val id = binding.radioGroup.checkedRadioButtonId
            when{
                TextUtils.isEmpty(binding.etName.text.toString().trim{ it <= ' '})-> {
                    binding.etName.error = "Nama produk tidak boleh kosong"
                }

                TextUtils.isEmpty(binding.etPrice.text.toString().trim{ it <= ' '})-> {
                    binding.etPrice.error = "Harga satuan tidak boleh kosong"
                }
                id!=-1 ->{
                    val name = binding.etName.text.toString()
                    val price = binding.etPrice.text.toString()
                    radioString = when (id) {
                        R.id.rb_food -> {
                            "food"
                        }
                        R.id.rb_drink -> {
                            "drink"
                        }
                        else -> {
                            "snack"
                        }
                    }
                    sendProduct(data, name, price, radioString)
                    Toast.makeText(this, "Produk berhasil ditambahlam", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, OwnerActivity::class.java))
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Pilih kategori terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun sendProduct(data: OwnerResponse, name: String, price: String, radioString: String) {
        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
        val reference = firebaseDb.getReference("stand")
        val child = reference.child(data.stand).child(radioString)
        val id = child.push().key as String
        val produkResponse = ProdukResponse(
            name,
            data.stand,
            id,
            data.name,
            price.toInt()
        )
        child.child(id).setValue(produkResponse)
    }


}