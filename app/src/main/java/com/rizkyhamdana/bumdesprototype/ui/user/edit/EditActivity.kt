package com.rizkyhamdana.bumdesprototype.ui.user.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityEditBinding
import com.rizkyhamdana.bumdesprototype.ui.user.MainActivity
import com.rizkyhamdana.bumdesprototype.util.Const

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    companion object{
        const val EXTRA_PROFILE = "extra_profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        title =  "Edit Profil"

        val dataUser = intent.getParcelableExtra<UserResponse>(EXTRA_PROFILE) as UserResponse
        setLayout(dataUser)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun setLayout(dataUser: UserResponse) {
        binding.apply {
            etName.setText(dataUser.name)
            etEmail.setText(dataUser.email)
            etAddress.setText(dataUser.address)
            etNumber.setText(dataUser.number)
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
                            "email" to dataUser.email,
                            "id" to dataUser.id,
                            "address" to address,
                            "number" to number,
                            "name" to name
                        )
                        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
                        firebaseDb.getReference("account").child("user").child(dataUser.id)
                            .updateChildren(updateProfile).addOnSuccessListener {
                            startActivity(Intent(this@EditActivity, MainActivity::class.java))
                            Toast.makeText(
                                this@EditActivity,
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