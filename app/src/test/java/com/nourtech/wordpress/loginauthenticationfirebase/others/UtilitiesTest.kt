package com.nourtech.wordpress.loginauthenticationfirebase.ui.fragments

import com.google.common.truth.Truth.assertThat
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities.PASSED
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities.SHOULD_CONTAINS_ALLOWED_CHARACTER
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities.SHOULD_CONTAINS_AT_LEAST_EIGHT_CHARACTERS
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities.SHOULD_CONTAINS_AT_LEAST_THREE_CHARACTERS
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities.SHOULD_NOT_BE_EMPTY
import com.nourtech.wordpress.loginauthenticationfirebase.others.Utilities.SHOULD_RETYPE_AND_PASSWORD_BE_SAME
import org.junit.Test


class UtilitiesTest {

    @Test
    fun `is field empty`(){
        val password = ""
        val check = Utilities.checkPassword(password)
        assertThat(check).isEqualTo(SHOULD_NOT_BE_EMPTY)
    }

    @Test
    fun `has at least three characters`(){
        val password = "af"
        val check = Utilities.checkPassword(password)
        assertThat(check).isEqualTo(SHOULD_CONTAINS_AT_LEAST_EIGHT_CHARACTERS)
    }

    @Test
    fun `has allowed characters only`(){
        val password = "abo*ahmad"
        val check = Utilities.checkPassword(password)
        assertThat(check).isEqualTo(SHOULD_CONTAINS_ALLOWED_CHARACTER)
    }

    @Test
    fun `password should pass`(){
        val password = "abc_1234"
        val check = Utilities.checkPassword(password)
        assertThat(check).isEqualTo(PASSED)
    }

    @Test
    fun `password retype should not be empty`(){
        val password = "abc_1234"
        val retype = ""
        val check = Utilities.checkRetypePassword(password, retype)
        assertThat(check).isEqualTo(SHOULD_NOT_BE_EMPTY)
    }

    @Test
    fun `password and retype password should be same`(){
        val password = "abc_1234"
        val retype = "abcd_123"
        val check = Utilities.checkRetypePassword(password, retype)
        assertThat(check).isEqualTo(SHOULD_RETYPE_AND_PASSWORD_BE_SAME)
    }

    @Test
    fun `retype password should pass`(){
        val password = "abc_1234"
        val retype = "abc_1234"
        val check = Utilities.checkRetypePassword(password, retype)
        assertThat(check).isEqualTo(PASSED)
    }

    @Test
    fun `check for username is empty`(){
        val username = ""
        val check = Utilities.checkForUsername(username)
        assertThat(check).isEqualTo(SHOULD_NOT_BE_EMPTY)
    }

    @Test
    fun `check for username too short`(){
        val username = "ab"
        val check = Utilities.checkForUsername(username)
        assertThat(check).isEqualTo(SHOULD_CONTAINS_AT_LEAST_THREE_CHARACTERS)
    }

    @Test
    fun `check for nick name is empty`(){
        val username = ""
        val check = Utilities.checkForNickName(username)
        assertThat(check).isEqualTo(SHOULD_NOT_BE_EMPTY)
    }



}