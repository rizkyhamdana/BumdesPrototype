package com.rizkyhamdana.bumdesprototype.ui.user.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizkyhamdana.bumdesprototype.ui.user.detail.cemilan.DetailCemilanFragment
import com.rizkyhamdana.bumdesprototype.ui.user.detail.makanan.DetailMakananFragment
import com.rizkyhamdana.bumdesprototype.ui.user.detail.minuman.DetailMinumanFragment

class DetailPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = DetailMakananFragment()
            1 -> fragment = DetailMinumanFragment()
            2 -> fragment = DetailCemilanFragment()
        }
        return fragment as Fragment
    }

}