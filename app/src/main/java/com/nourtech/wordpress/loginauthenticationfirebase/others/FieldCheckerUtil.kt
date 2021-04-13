package com.nourtech.wordpress.loginauthenticationfirebase.others

    const val PASSED = 0
    const val NOT_PASSED_EMPTY = 1
    const val NOT_PASSED_TOO_SHORT = 2
    const val NOT_PASSED_TOO_LONG = 3
    const val NOT_PASSED_INVALID = 4
    const val NOT_PASSED_MISMATCH = 5

    private const val REGEX_EMAIL_CHECK = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"

    private const val REGEX_PASSWORD_CHECK = "^(?=.*[0-9])(?=.*[a-zA-Z])."


    fun checkPassword(password: String): Int {
        return when (true) {
            password.isEmpty() -> NOT_PASSED_EMPTY
            password.length < 8 -> NOT_PASSED_TOO_SHORT
            password.length > 20 -> NOT_PASSED_TOO_LONG
            !password.contains(REGEX_PASSWORD_CHECK.toRegex()) -> NOT_PASSED_INVALID
            else -> PASSED
        }
    }

    fun checkRetypePassword(password: String, retype: String): Int {
        return when (true) {
            retype.isEmpty() -> NOT_PASSED_EMPTY
            retype != password -> NOT_PASSED_MISMATCH
            else -> PASSED
        }
    }

    fun checkForUsername(username: String): Int {
        return when (true) {
            username.isEmpty() -> NOT_PASSED_EMPTY
            !username.contains(REGEX_EMAIL_CHECK.toRegex()) -> NOT_PASSED_INVALID
            else -> PASSED
        }
    }

    fun checkForNickName(nickname: String): Int {
        return when (true) {
            nickname.isEmpty() -> NOT_PASSED_EMPTY
            else -> PASSED
        }
    }