package com.rizkyhamdana.bumdesprototype.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.databinding.FragmentRegisterBinding


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
        binding.btnregister.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.etName.text.toString().trim{ it <= ' '})-> {
                    binding.etName.error = "Nama tidak boleh kosong"
                }

                TextUtils.isEmpty(binding.etEmail.text.toString().trim{ it <= ' '})-> {
                    binding.etEmail.error = "Email tidak boleh kosong"
                }
                TextUtils.isEmpty(binding.etPassword.text.toString().trim{ it <= ' '})-> {
                    binding.etPassword.error = "Password tidak boleh kosong"
                }

                TextUtils.isEmpty(binding.etRepeatPassword.text.toString().trim{ it <= ' '})-> {
                    binding.etRepeatPassword.error = "Password tidak boleh kosong"
                }
                binding.etPassword.text.toString().trim{ it <= ' '} !=
                        binding.etRepeatPassword.text.toString().trim{ it <= ' '} -> {
                    binding.etRepeatPassword.error = "Password tidak sama"
                }
                else -> {
                    val email = binding.etEmail.text.toString().trim{ it <= ' '}
                    val password = binding.etPassword.text.toString().trim{ it <= ' '}
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener{ task ->
                            if (task.isSuccessful){
                                Toast.makeText(context, "Berhasil daftar ges", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Gagal daftar ges", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }

    }

}