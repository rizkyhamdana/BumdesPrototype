package com.rizkyhamdana.bumdesprototype.ui.intro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rizkyhamdana.bumdesprototype.databinding.ActivityIntroBinding
import com.rizkyhamdana.bumdesprototype.ui.login.LoginActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMulai.setOnClickListener {
            introFinished()
            finish()
        }
    }

    private fun introFinished(){
        startActivity(Intent(this, LoginActivity::class.java))
        val sharedPref = this.getSharedPreferences("intro", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}