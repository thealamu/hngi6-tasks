package com.vague.android.vague_task1.dashboard

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vague.android.vague_task1.R
import com.vague.android.vague_task1.common.showError
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val username = sharedPref?.getString(getString(R.string.user_name_pref_key), null)
        val email = sharedPref?.getString(getString(R.string.user_email_pref_key), null)

        if (email == null || username == null) {
            showError(view, "Cannot load user data")
            return
        }

        dash_email.text = email
        dash_username.text = username

        dash_logout.setOnClickListener {
            with(sharedPref.edit()) {
                putBoolean(getString(R.string.user_logged_in), false)
                commit()
            }
            view.findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
        }
    }
}