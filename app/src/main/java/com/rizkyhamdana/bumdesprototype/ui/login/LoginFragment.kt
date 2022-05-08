package com.rizkyhamdana.bumdesprototype.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.FragmentLoginBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity
import com.rizkyhamdana.bumdesprototype.ui.user.MainActivity

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth : FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel
    private var isOwner : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindProgressButton(binding.btnLogin)

        binding.btnLogin.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.etEmail.text.toString().trim{ it <= ' '})-> {
                    binding.etEmail.error = "Email tidak boleh kosong"
                }

                TextUtils.isEmpty(binding.etPassword.text.toString().trim{ it <= ' '})-> {
                    binding.etPassword.error = "Password tidak boleh kosong"
                }

                else -> {
                    binding.btnLogin.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                    val email = binding.etEmail.text.toString()
                    val password = binding.etPassword.text.toString()
                    loginViewModel.getAllOwner().observe(viewLifecycleOwner){
                        for(i in it){
                            if (i.email == email){
                                isOwner = true
                                break
                            }
                        }
                    }

                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                binding.btnLogin.hideProgress(R.string.berhasil)
                                if (isOwner){
                                    val intent = Intent(activity, OwnerActivity::class.java)
                                    startActivity(intent)
                                    activity?.finish()
                                }else{
                                    startActivity(Intent(activity, MainActivity::class.java))
                                    activity?.finish()
                                }
                            }
                            else{
                                binding.btnLogin.hideProgress(R.string.gagal)
                                Toast.makeText(context, "Gagal login, periksa kembali akun anda!", Toast.LENGTH_SHORT).show()
                            }
                        }

                }
            }

        }
        binding.tvForgotPass.setOnClickListener {
            val resetEmail = EditText(it.context)
            val passwordResetDialog = AlertDialog.Builder(it.context)
            passwordResetDialog.setTitle("Reset password?")
            passwordResetDialog.setMessage("Masukkan email kamu untuk menerima link reset password!")
            passwordResetDialog.setView(resetEmail)
            passwordResetDialog.setPositiveButton("Reset"
            ) { _, _ ->
                val email = resetEmail.text.toString()
                mAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Link reset password terkirim ke email!",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Link reset password gagal terkirim!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            passwordResetDialog.setNegativeButton("Batal"
            ){ _, _ ->

            }
            passwordResetDialog.create().show()
        }
    }
}