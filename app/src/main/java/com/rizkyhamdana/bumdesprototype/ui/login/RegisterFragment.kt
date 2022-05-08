package com.rizkyhamdana.bumdesprototype.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentRegisterBinding
import com.rizkyhamdana.bumdesprototype.util.Const


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindProgressButton(binding.btnregister)

        binding.btnregister.setOnClickListener {
            binding.btnregister.showProgress{
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }
            when{
                TextUtils.isEmpty(binding.etName.text.toString().trim{ it <= ' '})-> {
                    binding.etName.error = "Nama tidak boleh kosong"
                }

                TextUtils.isEmpty(binding.etEmail.text.toString().trim{ it <= ' '})-> {
                    binding.etEmail.error = "Email tidak boleh kosong"
                }
                TextUtils.isEmpty(binding.etAddress.text.toString().trim{ it <= ' '})-> {
                    binding.etAddress.error = "Alamat tidak boleh kosong"
                }
                TextUtils.isEmpty(binding.etNumber.text.toString().trim{ it <= ' '})-> {
                    binding.etNumber.error = "Nomor HP tidak boleh kosong"
                }
                TextUtils.isEmpty(binding.etPassword.text.toString().trim{ it <= ' '})-> {
                    binding.etPassword.error = "Password tidak boleh kosong"
                }
                binding.etPassword.text.toString().trim{ it <= ' '}.length < 6 -> {
                    binding.etPassword.error = "Password harus lebih dari 6 karakter"
                }

                else -> {
                    val email = binding.etEmail.text.toString().trim{ it <= ' '}
                    val name = binding.etName.text.toString().trim{ it <= ' '}
                    val address = binding.etAddress.text.toString().trim{ it <= ' '}
                    val number = binding.etNumber.text.toString().trim{ it <= ' '}
                    val password = binding.etPassword.text.toString().trim{ it <= ' '}
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener{ task ->
                            if (task.isSuccessful){
                                val user = mAuth.currentUser
                                sendUserData(user, email, name, address, number)
                                binding.btnregister.hideProgress(R.string.berhasil)
                                mAuth.signOut()
                            }else{
                                Toast.makeText(context, "Pendaftaran gagal. periksa kembali email anda!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }

    }

    private fun sendUserData(
        user: FirebaseUser?,
        email: String,
        name: String,
        address: String,
        number: String
    ) {
        val id = user?.uid
        val dataUser = UserResponse(
            email,
            id!!,
            address,
            number,
            name
        )
        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
        val reference = firebaseDb.getReference("account")
        reference.child("user")
            .child(id).setValue(dataUser)
    }


}