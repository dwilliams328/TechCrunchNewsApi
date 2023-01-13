package com.example.techcrunchnewsapi.ui.register

import com.google.firebase.auth.AuthResult

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: AuthResult? = null,
    val error: Int? = null
)