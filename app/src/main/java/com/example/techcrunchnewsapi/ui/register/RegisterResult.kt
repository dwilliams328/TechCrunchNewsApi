package com.example.techcrunchnewsapi.ui.register

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

/**
 * Authentication result : success (user details) or error message.
 */
data class SigninResult(
    val success: FirebaseUser? = null,
    val error: Int? = null
)