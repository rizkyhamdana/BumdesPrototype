package com.rizkyhamdana.bumdesprototype.ui.owner.home.cemilan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentListProductBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.home.OwnerHomeViewModel
import com.rizkyhamdana.bumdesprototype.ui.user.home.adapter.ListProdukAdapter

class OwnerCemilanFragment(private val stand: String): Fragment() {

    private var _binding: FragmentListProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListProdukAdapter
    private lateinit var homeViewModel: OwnerHomeViewModel

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeViewModel =
            ViewModelProvider(this)[OwnerHomeViewModel::class.java]
        adapter = ListProdukAdapter()
        mAuth = FirebaseAuth.getInstance()
        _binding = FragmentListProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        homeViewModel.getSnackbyStand(stand).observe(viewLifecycleOwner) { produk ->
            if(produk.isEmpty()){
                binding.layoutNodata.root.visibility = View.VISIBLE
            }else{
                adapter.setProduk(produk)
            }
        }
        adapter.setOnItemClickCallback(object : ListProdukAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ProdukResponse) {
            }

        })
    }
}
