package com.rizkyhamdana.bumdesprototype.ui.owner.message

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.data.UserResponse
import com.rizkyhamdana.bumdesprototype.databinding.FragmentOwnerMessageBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.message.OwnerChatActivity.Companion.EXTRA_ID
import com.rizkyhamdana.bumdesprototype.ui.owner.message.OwnerChatActivity.Companion.EXTRA_MYID
import com.rizkyhamdana.bumdesprototype.ui.owner.message.OwnerChatActivity.Companion.EXTRA_NAME


class OwnerMessageFragment : Fragment() {

    private var _binding: FragmentOwnerMessageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var messageViewModel: OwnerMessageViewModel
    private lateinit var adapter: OwnerMessageAdapter
    private lateinit var mAuth: FirebaseAuth
    private var stand : String = " "


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        messageViewModel =
            ViewModelProvider(this)[OwnerMessageViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        adapter = OwnerMessageAdapter()
        _binding = FragmentOwnerMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idOwner = mAuth.currentUser?.uid as String
        messageViewModel.getOwnerById(idOwner).observe(viewLifecycleOwner) {
            stand = it.stand
            messageViewModel.getAllChat(stand).observe(viewLifecycleOwner) { listString ->
                messageViewModel.getAllUser().observe(viewLifecycleOwner) { user ->
                    val listUser = ArrayList<UserResponse>()
                    for (i in listString) {
                        for (list in user) {
                            if (i == list.id) {
                                listUser.add(list)
                            }
                        }
                    }
                    binding.apply {
                        rvMessages.adapter = adapter
                        rvMessages.layoutManager = LinearLayoutManager(context)
                        adapter.setList(listUser)
                    }
                    adapter.setOnItemClickCallback(object : OwnerMessageAdapter.OnItemClickCallback{
                        override fun onItemClicked(data: UserResponse) {
                            val intent = Intent(activity, OwnerChatActivity::class.java)
                            intent.putExtra(EXTRA_ID, data.id)
                            intent.putExtra(EXTRA_NAME, data.name)
                            intent.putExtra(EXTRA_MYID, stand)
                            startActivity(intent)
                        }
                    })
                }
            }
        }

    }

}