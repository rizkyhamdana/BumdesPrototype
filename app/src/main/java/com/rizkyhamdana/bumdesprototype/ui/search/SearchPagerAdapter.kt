package com.rizkyhamdana.bumdesprototype.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizkyhamdana.bumdesprototype.ui.search.cemilan.SearchCemilanFragment
import com.rizkyhamdana.bumdesprototype.ui.search.makanan.SearchMakananFragment
import com.rizkyhamdana.bumdesprototype.ui.search.minuman.SearchMinumanFragment

class SearchPagerAdapter(activity: FragmentActivity, val query: String): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = SearchMakananFragment(query)
            1 -> fragment = SearchMinumanFragment(query)
            2 -> fragment = SearchCemilanFragment(query)
        }
        return fragment as Fragment
    }

}