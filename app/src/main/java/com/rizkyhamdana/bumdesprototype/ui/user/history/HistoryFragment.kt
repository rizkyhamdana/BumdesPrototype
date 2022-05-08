package com.rizkyhamdana.bumdesprototype.ui.user.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.OrderResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentHistoryBinding
import com.rizkyhamdana.bumdesprototype.ui.user.history.DetailHistoryActivity.Companion.EXTRA_HISTORY

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter

    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        adapter = HistoryAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idUser = mAuth.currentUser?.uid

        historyViewModel.getAllOrder(idUser!!).observe(viewLifecycleOwner){ order ->
            if (order.isNullOrEmpty()){
                binding.layoutNodata.root.visibility = View.VISIBLE
            }else{
                binding.rvOrders.adapter = adapter
                binding.rvOrders.layoutManager = LinearLayoutManager(context)
                adapter.setOrder(order)
            }
        }



        adapter.setOnItemClickCallback(object : HistoryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: OrderResponse) {
                val intent = Intent(activity, DetailHistoryActivity::class.java)
                intent.putExtra(EXTRA_HISTORY, data)
                startActivity(intent)
            }

        })
    }

}