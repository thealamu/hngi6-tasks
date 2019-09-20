package com.vague.android.vague_task1.common

import android.view.View
import android.widget.Toast
import androidx.core.util.PatternsCompat

fun showError(view: View, str: String) {
    //Snackbar.make(view, str, Snackbar.LENGTH_LONG).show()
    Toast.makeText(view.context, str, Toast.LENGTH_LONG).show()
}

fun showMessage(view: View, str: String) {
    //Snackbar.make(view, str, Snackbar.LENGTH_LONG).show()
    Toast.makeText(view.context, str, Toast.LENGTH_LONG).show()
}

fun isValidEmail(emailStr: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(emailStr).matches()
}