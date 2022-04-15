package com.rizkyhamdana.bumdesprototype.ui.owner.edit

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityEditBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity
import com.rizkyhamdana.bumdesprototype.util.Const

class OwnerEditActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    companion object{
        const val EXTRA_PROFILE = "extra_profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        title = "Edit Profil"
        val data = intent.getParcelableExtra<OwnerResponse>(EXTRA_PROFILE) as OwnerResponse
        setLayout(data)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setLayout(data: OwnerResponse) {
        binding.apply {
            etName.text = Editable.Factory.getInstance().newEditable(data.name)
            etEmail.text = Editable.Factory.getInstance().newEditable(data.email)
            etAddress.text = Editable.Factory.getInstance().newEditable(data.address)
            etNumber.text = Editable.Factory.getInstance().newEditable(data.number)
            btnSave.setOnClickListener{
                when {
                    TextUtils.isEmpty(etName.text.toString().trim { it <= ' ' }) -> {
                        etName.error = "Nama tidak boleh kosong"
                    }
                    TextUtils.isEmpty(etAddress.text.toString().trim { it <= ' ' }) -> {
                        etAddress.error = "Alamat tidak boleh kosong"
                    }
                    TextUtils.isEmpty(etNumber.text.toString().trim { it <= ' ' }) -> {
                        etNumber.error = "Nomor HP tidak boleh kosong"
                    }
                    else -> {
                        val name = etName.text.toString().trim { it <= ' ' }
                        val address = etAddress.text.toString().trim { it <= ' ' }
                        val number = etNumber.text.toString().trim { it <= ' ' }
                        val updateProfile = mapOf(
                            "email" to data.email,
                            "id" to data.id,
                            "address" to address,
                            "number" to number,
                            "name" to name,
                            "stand" to data.stand
                        )
                        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
                        firebaseDb.getReference("account").child("owner").child(data.id)
                            .updateChildren(updateProfile).addOnSuccessListener {
                            startActivity(Intent(this@OwnerEditActivity, OwnerActivity::class.java))
                            Toast.makeText(
                                this@OwnerEditActivity,
                                "Update profil berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                    }
                }
            }
        }
    }
}