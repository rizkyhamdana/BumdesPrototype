package com.rizkyhamdana.bumdesprototype.ui.owner.add

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityAddProductBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity
import com.rizkyhamdana.bumdesprototype.util.Const

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private var radioString: String = " "
    private var imageUri : Uri? = null

    companion object{
        const val EXTRA_OWNER = "extra_owner"
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                // There are no request codes
                val uri: Uri = data?.data!!
                imageUri = uri
                binding.imgProduk.setImageURI(uri)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Dibatalkan.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolBar)
        title = "Tambah Produk"

        val data = intent.getParcelableExtra<OwnerResponse>(EXTRA_OWNER) as OwnerResponse

        Glide.with(this)
            .load(Const.FOOD_IMAGE)
            .apply(RequestOptions())
            .into(binding.imgProduk)

        binding.fabUpload.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .cropSquare()//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)
                .galleryMimeTypes(  //Exclude gif images
                    mimeTypes = arrayOf(
                        "image/png",
                        "image/jpg",
                        "image/jpeg"
                    )
                )//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    resultLauncher.launch(intent)
                }
        }

        bindProgressButton(binding.btnAdd)

        binding.btnAdd.setOnClickListener {
            binding.btnAdd.showProgress {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }
            val id = binding.radioGroup.checkedRadioButtonId
            when{
                imageUri == null -> {
                    Toast.makeText(this, "Upload gambar produk terlebih dahulu.", Toast.LENGTH_SHORT).show()
                }
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
                    sendProduct(data, name, price, radioString, imageUri!!)

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

    private fun sendProduct(data: OwnerResponse, name: String, price: String, radioString: String, imageUri: Uri) {
        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
        val reference = firebaseDb.getReference("stand")
        val child = reference.child(data.stand).child(radioString)
        val id = child.push().key as String
        val firebaseStorage = FirebaseStorage.getInstance().reference.child(data.stand)
        val fileRef = firebaseStorage.child("${System.currentTimeMillis()}.${getFileExt(imageUri)}")
        fileRef.putFile(imageUri).addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener { imageUri ->
                val imageUrl = imageUri.toString()
                val produkResponse = ProdukResponse(
                    name,
                    data.stand,
                    imageUrl,
                    id,
                    data.name,
                    price.toInt()
                )
                child.child(id).setValue(produkResponse)
                Toast.makeText(
                    this@AddProductActivity,
                    "Produk berhasil ditambahkan.",
                    Toast.LENGTH_SHORT
                ).show()
                binding.btnAdd.hideProgress(R.string.berhasil)
                startActivity(Intent(this@AddProductActivity, OwnerActivity::class.java))
                finish()
            }
        }.addOnFailureListener {

            binding.btnAdd.hideProgress(R.string.gagal)
            Toast.makeText(
                this@AddProductActivity,
                "Produk gagal ditambahkan.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getFileExt(imageUri: Uri): String {
        return MimeTypeMap.getFileExtensionFromUrl(imageUri.toString())
    }


}