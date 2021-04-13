package com.nourtech.wordpress.loginauthenticationfirebase.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.nourtech.wordpress.loginauthenticationfirebase.R
import com.nourtech.wordpress.loginauthenticationfirebase.databinding.FragmentSignUpBinding
import com.nourtech.wordpress.loginauthenticationfirebase.others.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment: Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding.btnSignUp.setOnClickListener {
            if (checkField())
                signUp()
        }

    }

    private fun signUp() {
        CoroutineScope(Dispatchers.IO).launch {

            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            try {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(R.id.action_signUpFragment_to_profileFragment)
                        Snackbar.make(requireView(), "Account created.", Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(requireView(), "Sign up failed.", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkField(): Boolean {
        var passed = true
        val username = binding.etUsername.text.toString()
        //val nickname = binding.etUsername.text.toString() todo
        val password = binding.etPassword.text.toString()
        val retypePassword = binding.etRetypePassword.text.toString()

        binding.tvUsernameErrorMessage.apply {
            when (checkForUsername(username)) {
                NOT_PASSED_EMPTY -> {
                    text = context.getString(R.string.empty_field_error_message)
                    visibility = View.VISIBLE
                    passed = false
                }
                NOT_PASSED_TOO_SHORT -> {
                    text = context.getString(R.string.short_username_error_message)
                    visibility = View.VISIBLE
                    passed = false
                }
                NOT_PASSED_INVALID -> {
                    text = context.getString(R.string.email_is_invalid)
                    visibility = View.VISIBLE
                    passed = false
                }

                PASSED -> visibility = View.GONE
            }
        }

        binding.tvPasswordErrorMessage.apply {
            when (checkPassword(password)) {
                NOT_PASSED_EMPTY -> {
                    text = context.getString(R.string.empty_field_error_message)
                    visibility = View.VISIBLE
                    passed = false
                }
                NOT_PASSED_TOO_SHORT -> {
                    text = context.getString(R.string.short_password_error_massage)
                    visibility = View.VISIBLE
                    passed = false
                }
                NOT_PASSED_TOO_LONG -> {
                    text = context.getString(R.string.too_long_password_error_message)
                    visibility = View.VISIBLE
                    passed = false
                }
                NOT_PASSED_INVALID -> {
                    text = context.getString(R.string.invalid_password_massage)
                    visibility = View.VISIBLE
                    passed = false
                }
                PASSED -> visibility = View.GONE
            }
        }

        binding.tvRetypePasswordErrorMessage.apply {
            when (checkRetypePassword(password, retypePassword)) {
                NOT_PASSED_EMPTY -> {
                    text = context.getString(R.string.empty_field_error_message)
                    visibility = View.VISIBLE
                    passed = false
                }
                NOT_PASSED_MISMATCH -> {
                    text = context.getString(R.string.retype_password_mismatch_error_massage)
                    visibility = View.VISIBLE
                    passed = false
                }

                PASSED -> visibility = View.GONE
            }
        }
        return passed
    }
}