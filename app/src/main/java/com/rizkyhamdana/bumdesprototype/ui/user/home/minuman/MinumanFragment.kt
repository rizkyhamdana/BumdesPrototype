package com.rizkyhamdana.bumdesprototype.ui.user.home.minuman

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.ProdukResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentListProductBinding
import com.rizkyhamdana.bumdesprototype.ui.user.detail.DetailProductActivity
import com.rizkyhamdana.bumdesprototype.ui.user.home.HomeViewModel
import com.rizkyhamdana.bumdesprototype.ui.user.home.adapter.ListProdukAdapter


class MinumanFragment : Fragment() {

    private var _binding: FragmentListProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListProdukAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mAuth : FirebaseAuth
    private var idUser: String = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        adapter = ListProdukAdapter()
        _binding = FragmentListProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        homeViewModel.getDrinkPopular().observe(viewLifecycleOwner){ produk ->
            adapter.setProduk(produk)
        }
        homeViewModel.getAllUser().observe(viewLifecycleOwner){
            val emailLogin = mAuth.currentUser?.email
            for (i in it) {
                if (i.email == emailLogin) {
                    idUser = i.id
                }
            }
        }
        adapter.setOnItemClickCallback(object : ListProdukAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ProdukResponse) {
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_PRODUCT, data)

                intent.putExtra(DetailProductActivity.EXTRA_USER, idUser)
                startActivity(intent)
            }

        })
    }


}