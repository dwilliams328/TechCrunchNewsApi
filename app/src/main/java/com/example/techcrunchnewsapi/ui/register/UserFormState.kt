package com.example.techcrunchnewsapi.ui.register

/**
 * Data validation state of the registration form.
 */
data class UserFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)