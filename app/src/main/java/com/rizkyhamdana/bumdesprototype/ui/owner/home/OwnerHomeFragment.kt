package com.rizkyhamdana.bumdesprototype.ui.owner.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.FragmentOwnerHomeBinding
import com.rizkyhamdana.bumdesprototype.util.Const

class OwnerHomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewModel: OwnerHomeViewModel
    private var _binding: FragmentOwnerHomeBinding? = null
    private lateinit var pagerAdapter : OwnerPagerAdapter
    private val binding get() = _binding!!
    private var stand : String = " "

    companion object{
        private val TAB_TITLES = intArrayOf(
            R.string.tab2_text_1,
            R.string.tab2_text_2,
            R.string.tab2_text_3
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this)[OwnerHomeViewModel::class.java]
        _binding = FragmentOwnerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllOwner().observe(viewLifecycleOwner){
            val emailLogin = mAuth.currentUser?.email
            for (i in it){
                if (i.email == emailLogin){
                    stand = i.stand

                }
            }
        }
        viewModel.getAllStand().observe(viewLifecycleOwner){
            for (i in it){
                if (i.id == stand){
                    binding.apply {
                        tvNameStand.text = i.name
                        pagerAdapter = OwnerPagerAdapter(this@OwnerHomeFragment, i.id)
                        vpProduct.adapter = pagerAdapter
                        TabLayoutMediator(tabLayout, vpProduct) { tab, position ->
                            tab.text = resources.getString(TAB_TITLES[position])
                        }.attach()
                        Glide.with(this@OwnerHomeFragment)
                            .load(Const.STAND_IMAGE)
                            .apply(RequestOptions())
                            .into(imgStand)
                    }
                }
            }
        }
    }

}