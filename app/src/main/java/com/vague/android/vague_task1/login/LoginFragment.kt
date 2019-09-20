package com.vague.android.vague_task1.login

import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import com.vague.android.vague_task1.R
import com.vague.android.vague_task1.common.isEmpty
import com.vague.android.vague_task1.common.showError
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorTitleText()
        btn_login.setOnClickListener {
            // Do emptiness validations
            if (edit_email.isEmpty()) {
                showError(it, "E-mail cannot be empty")
            }
            if (edit_password.isEmpty()) {
                showError(it, "Password cannot be empty")
            }
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