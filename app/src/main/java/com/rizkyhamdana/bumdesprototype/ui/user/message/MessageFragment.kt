package com.rizkyhamdana.bumdesprototype.ui.user.message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.OwnerResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentMessageBinding
import com.rizkyhamdana.bumdesprototype.ui.user.message.chat.ChatActivity
import com.rizkyhamdana.bumdesprototype.ui.user.message.chat.ChatActivity.Companion.EXTRA_ID
import com.rizkyhamdana.bumdesprototype.ui.user.message.chat.ChatActivity.Companion.EXTRA_MYID
import com.rizkyhamdana.bumdesprototype.ui.user.message.chat.ChatActivity.Companion.EXTRA_NAME

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var messageViewModel: MessageViewModel
    private lateinit var adapter: MessageAdapter
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        messageViewModel =
            ViewModelProvider(this)[MessageViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        adapter = MessageAdapter()
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idUser = mAuth.currentUser?.uid
        messageViewModel.getAllOwner().observe(viewLifecycleOwner){
            binding.apply {
                rvMessages.adapter = adapter
                rvMessages.layoutManager = LinearLayoutManager(context)
                adapter.setList(it)
            }
        }
        adapter.setOnItemClickCallback(object : MessageAdapter.OnItemClickCallback{
            override fun onItemClicked(data: OwnerResponse) {
                val intent = Intent(activity, ChatActivity::class.java)
                intent.putExtra(EXTRA_ID, data.stand)
                intent.putExtra(EXTRA_NAME, data.name)
                intent.putExtra(EXTRA_MYID, idUser)
                startActivity(intent)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}