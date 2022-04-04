package com.rizkyhamdana.bumdesprototype.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.ActivityLoginBinding

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

        sectionPagerAdapter = SectionPagerAdapter(this)
        binding.apply {
            vpLogin.adapter = sectionPagerAdapter
            TabLayoutMediator(tabLayout, vpLogin) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }




    }
}