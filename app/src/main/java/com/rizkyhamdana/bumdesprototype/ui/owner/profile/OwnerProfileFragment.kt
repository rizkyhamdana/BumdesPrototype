package com.rizkyhamdana.bumdesprototype.ui.owner.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.databinding.FragmentOwnerProfileBinding
import com.rizkyhamdana.bumdesprototype.ui.login.LoginActivity
import com.rizkyhamdana.bumdesprototype.ui.owner.edit.OwnerEditActivity
import com.rizkyhamdana.bumdesprototype.util.Const


class OwnerProfileFragment : Fragment() {

    private var _binding: FragmentOwnerProfileBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewModel: OwnerProfileViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[OwnerProfileViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()

        _binding = FragmentOwnerProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idOwner = mAuth.currentUser?.uid
        viewModel.getOwnerById(idOwner!!).observe(viewLifecycleOwner){
            binding.apply {
                tvName.text = it.name
                tvEmail.text = it.email
                tvAddress.text = it.address
                tvNumber.text = it.number
                Glide.with(this@OwnerProfileFragment)
                    .load(Const.PROFILE_IMAGE)
                    .apply(RequestOptions())
                    .into(imgProfile)
                btnEdit.setOnClickListener{ _ ->
                    val intent = Intent(activity, OwnerEditActivity::class.java)
                    intent.putExtra(OwnerEditActivity.EXTRA_PROFILE, it)
                    startActivity(intent)
                }
            }
        }
        binding.btnLogout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

    }
}