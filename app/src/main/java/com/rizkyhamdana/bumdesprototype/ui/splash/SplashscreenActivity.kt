package com.rizkyhamdana.bumdesprototype.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.databinding.ActivitySplashscreenBinding
import com.rizkyhamdana.bumdesprototype.ui.intro.IntroActivity
import com.rizkyhamdana.bumdesprototype.ui.login.LoginActivity
import com.rizkyhamdana.bumdesprototype.ui.login.LoginViewModel
import com.rizkyhamdana.bumdesprototype.ui.owner.OwnerActivity
import com.rizkyhamdana.bumdesprototype.ui.user.MainActivity
import com.rizkyhamdana.bumdesprototype.util.Const.MILLIS

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashscreenBinding
    private lateinit var mAuth : FirebaseAuth
    private var isLogin : Boolean = false

    private lateinit var loginViewModel: LoginViewModel
    private var isOwner : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()

        loginViewModel.getAllOwner().observe(this){
            if (mAuth.currentUser != null){
                isLogin = true
                val emailLogin = mAuth.currentUser?.email
                for (i in it){
                    if (i.email == emailLogin){
                        isOwner = true
                        break
                    }
                }
            }

        }
        Handler(mainLooper).postDelayed({
            when {
                isOwner -> {
                    startActivity(Intent(this, OwnerActivity::class.java))
                }
                isLogin -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                introFinished() -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, IntroActivity::class.java))
                }
            }
            finish()
        }, MILLIS)
    }

    private fun introFinished(): Boolean{
        val sharedPref = this.getSharedPreferences("intro", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}