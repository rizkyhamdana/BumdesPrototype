package com.rizkyhamdana.bumdesprototype.ui.user.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.StandResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityDetailStandBinding
import com.rizkyhamdana.bumdesprototype.ui.user.home.HomeFragment
import com.rizkyhamdana.bumdesprototype.util.Const

class DetailStandActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailStandBinding
    private lateinit var pagerAdapter: DetailPagerAdapter

    companion object{
        const val EXTRA_STAND = "extra_stand"
        private val TAB_TITLES = intArrayOf(
            R.string.tab2_text_1,
            R.string.tab2_text_2,
            R.string.tab2_text_3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        title =  "Detail"


        val data = intent.getParcelableExtra<StandResponse>(EXTRA_STAND) as StandResponse
        setLayout(data)

        pagerAdapter = DetailPagerAdapter(this)
        binding.apply {
            vpProduct.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, vpProduct) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }



    }

    private fun setLayout(data: StandResponse) {
        binding.apply {
            tvNameStand.text = data.name
            Glide.with(this@DetailStandActivity)
                .load(
                    Const.STAND_IMAGE
                )
                .apply(RequestOptions())
                .into(imgStand)
        }
    }

}