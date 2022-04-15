package com.rizkyhamdana.bumdesprototype.ui.login

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.ActivityLoginBinding
import com.rizkyhamdana.bumdesprototype.ui.splash.SplashscreenActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var sectionPagerAdapter: SectionPagerAdapter

    companion object{
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawLayout()
    }

    private fun isNetworkAvailable(): Boolean{
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
    }

    private fun drawLayout() {
        if (isNetworkAvailable()) {
            binding.loginActivity.visibility = View.VISIBLE
            binding.layoutNoInteret.root.visibility = View.GONE
            sectionPagerAdapter = SectionPagerAdapter(this)
            binding.apply {
                vpLogin.adapter = sectionPagerAdapter
                TabLayoutMediator(tabLayout, vpLogin) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
        } else {
            binding.loginActivity.visibility = View.GONE
            binding.layoutNoInteret.root.visibility = View.VISIBLE
            binding.layoutNoInteret.btnTryAgain.setOnClickListener {
                startActivity(Intent(this, SplashscreenActivity::class.java))
                finish()
            }
        }
    }
}