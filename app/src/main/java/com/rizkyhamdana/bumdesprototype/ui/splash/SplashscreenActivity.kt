package com.rizkyhamdana.bumdesprototype.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rizkyhamdana.bumdesprototype.databinding.ActivitySplashscreenBinding
import com.rizkyhamdana.bumdesprototype.ui.intro.IntroActivity
import com.rizkyhamdana.bumdesprototype.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashscreenBinding

    companion object{
        const val MILLIS = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            if (introFinished()){
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
        }, MILLIS)
    }

    private fun introFinished(): Boolean{
        val sharedPref = this.getSharedPreferences("intro", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}