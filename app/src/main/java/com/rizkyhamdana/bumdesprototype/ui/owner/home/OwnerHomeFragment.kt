package com.rizkyhamdana.bumdesprototype.ui.owner.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentOwnerHomeBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.add.AddProductActivity
import com.rizkyhamdana.bumdesprototype.ui.owner.add.AddProductActivity.Companion.EXTRA_OWNER
import com.rizkyhamdana.bumdesprototype.util.Const

class OwnerHomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewModel: OwnerHomeViewModel
    private var _binding: FragmentOwnerHomeBinding? = null
    private lateinit var pagerAdapter : OwnerPagerAdapter
    private val binding get() = _binding!!
    private var stand : String = " "
    private var owner = OwnerResponse()

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
        val idOwner = mAuth.currentUser?.uid as String
        viewModel.getOwnerById(idOwner).observe(viewLifecycleOwner){
            stand = it.stand
            owner = it
            binding.apply {
                tvNameStand.text = it.name
                pagerAdapter = OwnerPagerAdapter(this@OwnerHomeFragment, it.stand)
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


        binding.fabAdd.setOnClickListener{
            val intent = Intent(activity, AddProductActivity::class.java)
            intent.putExtra(EXTRA_OWNER, owner)
            startActivity(intent)
        }
    }


}