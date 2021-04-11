package com.nourtech.wordpress.loginauthenticationfirebase.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nourtech.wordpress.loginauthenticationfirebase.R
import com.nourtech.wordpress.loginauthenticationfirebase.databinding.FragmentSignUpBinding

class SignUpFragment: Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
    }
}