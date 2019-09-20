package com.vague.android.vague_task1.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vague.android.vague_task1.R
import com.vague.android.vague_task1.common.isEmpty
import com.vague.android.vague_task1.common.isValidEmail
import com.vague.android.vague_task1.common.showError
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment(R.layout.fragment_signup) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_signup.setOnClickListener {
            doValidations(it)
        }
    }

    private fun doValidations(view: View) {
        // Check for emptiness
        if (signup_edit_name.isEmpty()) {
            showError(view, "Name cannot be empty")
            return
        }
        if (signup_edit_email.isEmpty()) {
            showError(view, "E-mail cannot be empty")
            return
        }
        if (signup_edit_password.isEmpty()) {
            showError(view, "Password cannot be empty")
            return
        }

        // Validate email
        if (!isValidEmail(signup_edit_email.text.toString())) {
            showError(view, "Invalid E-mail address")
            return
        }

        // Validate passwords
        val password = signup_edit_password.text?.toString()?.trim()
        val confpassword = signup_edit_confpassword.text?.toString()?.trim()

        if (!password.equals(confpassword)) {
            showError(view, "Passwords do not match")
            return
        }

        saveUserData()
        view.findNavController().navigate(R.id.action_signupFragment_to_dashboardFragment)
    }

    private fun saveUserData() {
        val name = signup_edit_name.text?.toString()?.trim()
        val email = signup_edit_email.text?.toString()?.trim()
        val password = signup_edit_password.text?.toString()?.trim()

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref!!.edit()) {
            putString(getString(R.string.user_name_pref_key), name)
            putString(getString(R.string.user_email_pref_key), email)
            putString(getString(R.string.user_password_pref_key), password)
            putBoolean(getString(R.string.user_logged_in), true)
            commit()
        }
    }
}