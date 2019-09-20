package com.vague.android.vague_task1.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showError(view: View, str: String) {
    Snackbar.make(view, str, Snackbar.LENGTH_LONG).show()
}
