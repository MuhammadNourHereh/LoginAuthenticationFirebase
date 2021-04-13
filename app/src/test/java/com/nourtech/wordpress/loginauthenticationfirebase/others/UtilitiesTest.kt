package com.nourtech.wordpress.loginauthenticationfirebase.others

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class UtilitiesTest {

    @Test
    fun `is field empty`() {
        val password = ""
        val check = checkPassword(password)
        assertThat(check).isEqualTo(NOT_PASSED_EMPTY)
    }

    @Test
    fun `has at least 8 characters`() {
        val password = "abc1234"
        val check = checkPassword(password)
        assertThat(check).isEqualTo(NOT_PASSED_TOO_SHORT)
    }

    @Test
    fun `has allowed characters only`() {
        val password = "abcdefghij1234567890G"
        val check = checkPassword(password)
        assertThat(check).isEqualTo(NOT_PASSED_TOO_LONG)
    }
    @Test
    fun `password is too simple`() {
        val password = "1234567890"
        val check = checkPassword(password)
        assertThat(check).isEqualTo(NOT_PASSED_INVALID)
    }

    @Test
    fun `password should pass`() {
        val password = "abc_1234"
        val check = checkPassword(password)
        assertThat(check).isEqualTo(PASSED)
    }

    @Test
    fun `password retype should not be empty`() {
        val password = "abc_1234"
        val retype = ""
        val check = checkRetypePassword(password, retype)
        assertThat(check).isEqualTo(NOT_PASSED_EMPTY)
    }

    @Test
    fun `password and retype password should be same`() {
        val password = "abc_1234"
        val retype = "abcd_123"
        val check = checkRetypePassword(password, retype)
        assertThat(check).isEqualTo(NOT_PASSED_MISMATCH)
    }

    @Test
    fun `retype password should pass`() {
        val password = "abc_1234"
        val retype = "abc_1234"
        val check = checkRetypePassword(password, retype)
        assertThat(check).isEqualTo(PASSED)
    }

    @Test
    fun `check for username is empty`() {
        val username = ""
        val check = checkForUsername(username)
        assertThat(check).isEqualTo(NOT_PASSED_EMPTY)
    }

    @Test
    fun `check for not valid email`() {
        val username = "ab@"
        val check = checkForUsername(username)
        assertThat(check).isEqualTo(NOT_PASSED_INVALID)
    }

    @Test
    fun `check for valid email`() {
        val username = "abc@abc.abc"
        val check = checkForUsername(username)
        assertThat(check).isEqualTo(PASSED)
    }

    @Test
    fun `check for nick name is empty`() {
        val username = ""
        val check = checkForNickName(username)
        assertThat(check).isEqualTo(NOT_PASSED_EMPTY)
    }


}