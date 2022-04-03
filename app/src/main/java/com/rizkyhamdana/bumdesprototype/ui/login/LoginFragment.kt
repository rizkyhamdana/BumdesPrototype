package com.rizkyhamdana.bumdesprototype.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.databinding.FragmentLoginBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.etEmail.text.toString().trim{ it <= ' '})-> {
                    binding.etEmail.error = "Email tidak boleh kosong"
                }

                TextUtils.isEmpty(binding.etPassword.text.toString().trim{ it <= ' '})-> {
                    binding.etPassword.error = "Password tidak boleh kosong"
                }

                else -> {
                    val email = binding.etEmail.text.toString().trim{ it <= ' '}
                    val password = binding.etPassword.text.toString().trim{ it <= ' '}
                    binding.etEmail.text.clear()
                    binding.etPassword.text.clear()
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Berhasil login ges", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(context, "Gagal login ges", Toast.LENGTH_SHORT).show()
                            }
                        }

                }
            }

        }
        binding.tvForgotPass.setOnClickListener {
            Toast.makeText(activity, "Anda menekan lupa password", Toast.LENGTH_SHORT).show()
        }
    }
}