package com.rizkyhamdana.bumdesprototype.ui.user.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.databinding.FragmentProfileBinding
import com.rizkyhamdana.bumdesprototype.ui.login.LoginActivity
import com.rizkyhamdana.bumdesprototype.ui.user.edit.EditActivity
import com.rizkyhamdana.bumdesprototype.ui.user.edit.EditActivity.Companion.EXTRA_PROFILE
import com.rizkyhamdana.bumdesprototype.util.Const.PROFILE_IMAGE


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var profileViewModel: ProfileViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel=
            ViewModelProvider(this)[ProfileViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idUser = mAuth.currentUser?.uid as String
        profileViewModel.getUserbyId(idUser).observe(viewLifecycleOwner){
            binding.apply {
                tvName.text = it.name
                tvEmail.text = it.email
                tvAddress.text = it.address
                tvNumber.text = it.number
                Glide.with(this@ProfileFragment)
                    .load(PROFILE_IMAGE)
                    .apply(RequestOptions())
                    .into(imgProfile)
                btnEdit.setOnClickListener{ _ ->
                    val intent = Intent(activity, EditActivity::class.java)
                    intent.putExtra(EXTRA_PROFILE, it)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}