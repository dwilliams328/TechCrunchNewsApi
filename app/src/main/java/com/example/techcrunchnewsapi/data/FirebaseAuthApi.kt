package com.example.techcrunchnewsapi.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class FirebaseAuthApi {

    fun register(username: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    fun login(username: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
    }
}