package com.nourtech.wordpress.loginauthenticationfirebase.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nourtech.wordpress.loginauthenticationfirebase.R
import com.nourtech.wordpress.loginauthenticationfirebase.databinding.FragmentLoginBinding
import com.nourtech.wordpress.loginauthenticationfirebase.others.*
import com.nourtech.wordpress.loginauthenticationfirebase.others.NOT_PASSED_EMPTY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        auth = FirebaseAuth.getInstance()

        binding.apply {
            tvSignUp.setOnClickListener {

                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            btnLogin.setOnClickListener {
                if (checkFields())
                    loginUser()
            }
        }
    }

    private fun loginUser() {
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {

            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                    } else {
                        binding.tvPasswordErrorMessage.text = "login failed"
                        binding.tvPasswordErrorMessage.visibility = VISIBLE
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkFields(): Boolean {
        var passed = true
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        binding.tvUsernameErrorMessage.apply {
            when (checkForUsername(username)) {
                NOT_PASSED_EMPTY -> {
                    text = context.getString(R.string.empty_field_error_message)
                    visibility = VISIBLE
                    passed = false
                }
                NOT_PASSED_TOO_SHORT -> {
                    text = context.getString(R.string.short_username_error_message)
                    visibility = VISIBLE
                    passed = false
                }
                NOT_PASSED_INVALID -> {
                    text = context.getString(R.string.email_is_invalid)
                    visibility = VISIBLE
                    passed = false
                }

                PASSED -> visibility = GONE
            }
        }

        binding.tvPasswordErrorMessage.apply {
            when (checkPassword(password)) {
                NOT_PASSED_EMPTY -> {
                    text = context.getString(R.string.empty_field_error_message)
                    visibility = VISIBLE
                    passed = false
                }
                NOT_PASSED_TOO_SHORT -> {
                    text = context.getString(R.string.short_password_error_massage)
                    visibility = VISIBLE
                    passed = false
                }
                NOT_PASSED_TOO_LONG -> {
                    text = context.getString(R.string.too_long_password_error_message)
                    visibility = VISIBLE
                    passed = false
                }
                NOT_PASSED_INVALID -> {
                    text = context.getString(R.string.invalid_password_massage)
                    visibility = VISIBLE
                    passed = false
                }
                PASSED -> visibility = GONE
            }
        }
        return passed
    }

}