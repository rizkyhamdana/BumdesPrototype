package com.rizkyhamdana.bumdesprototype.ui.user.detail.minuman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.data.StandResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentListProductBinding
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailProductActivity
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailStandActivity
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailViewModel
import com.rizkyhamdana.bumdesprototype.ui.user.home.adapter.ListProdukAdapter

class DetailMinumanFragment:  Fragment() {

    private var _binding: FragmentListProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListProdukAdapter
    private lateinit var homeViewModel: DetailViewModel
    private lateinit var mAuth : FirebaseAuth
    private var idUser: String = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeViewModel =
            ViewModelProvider(this)[DetailViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        adapter = ListProdukAdapter()
        _binding = FragmentListProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stand = activity?.intent?.getParcelableExtra<StandResponse>(DetailStandActivity.EXTRA_STAND) as StandResponse
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        homeViewModel.getAllUser().observe(viewLifecycleOwner){
            val emailLogin = mAuth.currentUser?.email
            for (i in it) {
                if (i.email == emailLogin) {
                    idUser = i.id
                }
            }
        }
        homeViewModel.getDrinkbyStand(stand.id).observe(viewLifecycleOwner) { produk ->
            adapter.setProduk(produk)
        }
        adapter.setOnItemClickCallback(object : ListProdukAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ProdukResponse) {
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_PRODUCT, data)
                intent.putExtra(DetailProductActivity.EXTRA_USER, idUser)
                startActivity(intent)
            }

        })
    }
}