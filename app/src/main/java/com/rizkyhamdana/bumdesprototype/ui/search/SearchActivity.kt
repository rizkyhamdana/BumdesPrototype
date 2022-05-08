package com.rizkyhamdana.bumdesprototype.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.ActivitySearchBinding
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailPagerAdapter
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailStandActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchPagerAdapter: SearchPagerAdapter

    companion object{
        const val EXTRA_QUERY = "extra_query"
        private val TAB_TITLES = intArrayOf(
            R.string.tab2_text_1,
            R.string.tab2_text_2,
            R.string.tab2_text_3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        val query = intent.getStringExtra(EXTRA_QUERY) as String

        title = "Cari: $query"


        searchPagerAdapter = SearchPagerAdapter(this, query)
        binding.apply {
            vpProduct.adapter = searchPagerAdapter
            TabLayoutMediator(tabLayout, vpProduct) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

}