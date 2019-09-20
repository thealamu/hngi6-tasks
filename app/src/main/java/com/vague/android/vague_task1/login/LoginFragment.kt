package com.vague.android.vague_task1.login

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.vague.android.vague_task1.R
import com.vague.android.vague_task1.common.isEmpty
import com.vague.android.vague_task1.common.isValidEmail
import com.vague.android.vague_task1.common.showError
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        // Has a user logged in before?
        Log.d(TAG, "In onCreate")
        val loggedin = sharedPref?.getBoolean(getString(R.string.user_logged_in), false)
        if (loggedin != null && loggedin == true) {
            Log.d(TAG, "Found a user")
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorTitleText()
        btn_login.setOnClickListener {
            doValidations(it)
        }

        btn_signup.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    private fun doValidations(view: View) {
        // Do emptiness validations
        if (edit_email.isEmpty()) {
            showError(view, "E-mail cannot be empty")
            return
        }
        if (edit_password.isEmpty()) {
            showError(view, "Password cannot be empty")
            return
        }

        // Do content validations
        if (!isValidEmail(edit_email.text.toString())) {
            showError(view, "Invalid E-mail address")
            return
        }

        // Validate credentials
        validateCredentials(view)
    }

    private fun validateCredentials(view: View) {
        var prefEmail: String? = null
        var prefPassword: String? = null

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.let { sp ->
            prefEmail = sp.getString(getString(R.string.user_email_pref_key), null)
            prefPassword = sp.getString(getString(R.string.user_password_pref_key), null)
        }

        if (prefEmail.isNullOrEmpty() || prefPassword.isNullOrEmpty()) {
            showError(view, "You do not have an account yet")
        } else {
            // Check that the credentials are correct
            val inputEmail = edit_email.text.toString()
            val inputPassword = edit_password.text.toString()

            if (inputEmail.equals(prefEmail)) {
                if (inputPassword.equals(prefPassword)) {
                    with(sharedPref!!.edit()) {
                        putBoolean(getString(R.string.user_logged_in), true)
                        commit()
                    }
                    view.findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                    return
                }
            }

            // Incorrect credentials
            showError(view, "Incorrect email or password, please try again")
        }
    }

    private fun colorTitleText() {
        // Change the color composition of displayed app name
        val appName = getString(R.string.app_actual_name)

        val spannableStringBuilder = SpannableStringBuilder(appName)
        val fcsSecondary = when {
            Build.VERSION.SDK_INT >= 23 -> ForegroundColorSpan(
                resources.getColor(
                    R.color.secondaryColor,
                    activity?.theme
                )
            )
            else -> ForegroundColorSpan(resources.getColor(R.color.secondaryColor))
        }
        spannableStringBuilder.setSpan(fcsSecondary, 4, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtAppName.text = spannableStringBuilder
    }

    companion object {
        val TAG = "LoginFragment"
    }
}