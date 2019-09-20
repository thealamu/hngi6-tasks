package com.vague.android.vague_task1.common

import android.widget.EditText

fun EditText?.isEmpty(): Boolean {
    return this?.text?.trim()?.isEmpty() ?: true
}
