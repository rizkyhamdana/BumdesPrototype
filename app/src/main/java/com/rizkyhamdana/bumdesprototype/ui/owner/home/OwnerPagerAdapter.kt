package com.rizkyhamdana.bumdesprototype.ui.owner.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizkyhamdana.bumdesprototype.ui.owner.home.cemilan.OwnerCemilanFragment
import com.rizkyhamdana.bumdesprototype.ui.owner.home.makanan.OwnerMakananFragment
import com.rizkyhamdana.bumdesprototype.ui.owner.home.minuman.OwnerMinumanFragment

class OwnerPagerAdapter(fragment: Fragment, private val stand: String): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = OwnerMakananFragment(stand)
            1 -> fragment = OwnerMinumanFragment(stand)
            2 -> fragment = OwnerCemilanFragment(stand)
        }
        return fragment as Fragment
    }

}