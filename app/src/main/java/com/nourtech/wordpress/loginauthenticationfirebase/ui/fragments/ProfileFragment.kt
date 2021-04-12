package com.nourtech.wordpress.loginauthenticationfirebase.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nourtech.wordpress.loginauthenticationfirebase.R
import com.nourtech.wordpress.loginauthenticationfirebase.databinding.FragmentProfileBinding

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        auth = FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigateUp()
        }
        val user = auth.currentUser

        if (user != null) {
            binding.tvNickname.text = user.email.split("@".toRegex(), 0)[0]
        }


    }
}