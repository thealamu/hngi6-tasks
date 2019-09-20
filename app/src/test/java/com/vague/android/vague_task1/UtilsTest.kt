package com.vague.android.vague_task1

import com.vague.android.vague_task1.common.isValidEmail
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UtilsTest {

    @Test
    fun isValidEmail() {
        var email = "vaguemail369@gmail.com"
        assertTrue(isValidEmail(email))

        email = "master@slave.com"
        assertTrue(isValidEmail(email))

        email = "alamu161@gmail.com"
        assertTrue(isValidEmail(email))
    }

    @Test
    fun isNotValidEmail() {
        var email = "sometext.you"
        assertFalse(isValidEmail(email))

        email = "a@a"
        assertFalse(isValidEmail(email))

        email = "kilometer@miles"
        assertFalse(isValidEmail(email))

        email = "@file.com"
        assertFalse(isValidEmail(email))
    }
}