package com.rizkyhamdana.bumdesprototype.ui.user.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentHomeBinding
import com.rizkyhamdana.bumdesprototype.ui.user.cart.CartActivity
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailStandActivity
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailStandActivity.Companion.EXTRA_STAND
import com.rizkyhamdana.bumdesprototype.ui.user.home.adapter.ListKedaiAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pagerAdapter: HomePagerAdapter
    private lateinit var vpAdapter: ListKedaiAdapter


    private lateinit var homeViewModel: HomeViewModel

    companion object{
        private val TAB_TITLES = intArrayOf(
            R.string.tab2_text_1,
            R.string.tab2_text_2,
            R.string.tab2_text_3
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        pagerAdapter = HomePagerAdapter(this)
        vpAdapter = ListKedaiAdapter()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            homeViewModel.getAllOwner().observe(viewLifecycleOwner){ stand ->
                vpShop.adapter = vpAdapter
                vpAdapter.setKedai(stand)
                indicator.setViewPager(vpShop)
            }
            vpProduct.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, vpProduct) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
            vpAdapter.setOnItemClickCallback(object : ListKedaiAdapter.OnItemClickCallback{
                override fun onItemClicked(data: OwnerResponse) {
                    val intent = Intent(activity, DetailStandActivity::class.java)
                    intent.putExtra(EXTRA_STAND, data)
                    startActivity(intent)
                }

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu.findItem(R.id.menu_search)
        val searchView : SearchView = searchItem.actionView as SearchView
        searchView.findViewById<View>(androidx.appcompat.R.id.search_plate).setBackgroundColor(Color.TRANSPARENT)
        searchView.queryHint = "Cari makanan...."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery(query, false)
                Toast.makeText(activity, "Looking for $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_cart -> {
                val intent = Intent(activity, CartActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }


}