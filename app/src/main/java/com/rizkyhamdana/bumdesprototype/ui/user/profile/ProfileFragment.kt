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

        profileViewModel.getAllUser().observe(viewLifecycleOwner){

            val emailLogin = mAuth.currentUser?.email
            val photo = mAuth.currentUser?.photoUrl
            for (i in it){
                if (i.email == emailLogin){
                    binding.apply {
                        tvName.text = i.name
                        tvEmail.text = i.email
                        tvAddress.text = i.address
                        tvNumber.text = i.number
                        Glide.with(this@ProfileFragment)
                            .load(photo)
                            .apply(RequestOptions())
                            .into(imgProfile)
                    }
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