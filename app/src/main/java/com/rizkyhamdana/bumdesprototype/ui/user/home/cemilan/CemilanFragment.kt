package com.rizkyhamdana.bumdesprototype.ui.user.home.cemilan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.FragmentHomeBinding
import com.rizkyhamdana.bumdesprototype.databinding.FragmentListProductBinding
import com.rizkyhamdana.bumdesprototype.databinding.ListProductBinding
import com.rizkyhamdana.bumdesprototype.ui.user.home.HomeViewModel
import com.rizkyhamdana.bumdesprototype.ui.user.home.adapter.ListProdukAdapter

class CemilanFragment : Fragment() {

    private var _binding: FragmentListProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListProdukAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        adapter = ListProdukAdapter()
        _binding = FragmentListProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        val produk = homeViewModel.setProduk(3)
        adapter.setProduk(produk)

    }

}