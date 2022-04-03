package com.rizkyhamdana.bumdesprototype.ui.user.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizkyhamdana.bumdesprototype.ui.user.home.cemilan.CemilanFragment
import com.rizkyhamdana.bumdesprototype.ui.user.home.makanan.MakananFragment
import com.rizkyhamdana.bumdesprototype.ui.user.home.minuman.MinumanFragment

class HomePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MakananFragment()
            1 -> fragment = MinumanFragment()
            2 -> fragment = CemilanFragment()
        }
        return fragment as Fragment
    }

}