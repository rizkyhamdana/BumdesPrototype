package com.rizkyhamdana.bumdesprototype.ui.owner.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.OrderResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentOwnerHistoryBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.history.DetailOwnerHistoryActivity.Companion.EXTRA_HISTORY
import com.rizkyhamdana.bumdesprototype.ui.user.history.HistoryAdapter

class OwnerHistoryFragment : Fragment() {

    private var _binding: FragmentOwnerHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: HistoryAdapter
    private lateinit var mAuth : FirebaseAuth
    private lateinit var historyViewModel: OwnerHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOwnerHistoryBinding.inflate(inflater, container, false)

        historyViewModel = ViewModelProvider(this)[OwnerHistoryViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        adapter = HistoryAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = mAuth.currentUser?.uid as String
        historyViewModel.getOwnerById(id).observe(viewLifecycleOwner){
            historyViewModel.getAllOrderbyStand(it.stand).observe(viewLifecycleOwner){ order ->
                if (order.isEmpty()){
                    binding.layoutNodata.root.visibility = View.VISIBLE
                }else{
                    binding.rvOrders.adapter = adapter
                    binding.rvOrders.layoutManager = LinearLayoutManager(context)
                    adapter.setOrder(order)
                }
            }
        }

        adapter.setOnItemClickCallback(object : HistoryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: OrderResponse) {
                val intent = Intent(activity, DetailOwnerHistoryActivity::class.java)
                intent.putExtra(EXTRA_HISTORY, data)
                startActivity(intent)
            }

        })
    }

}