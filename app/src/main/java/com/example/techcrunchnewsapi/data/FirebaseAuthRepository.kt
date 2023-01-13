package com.example.techcrunchnewsapi.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class FirebaseAuthRepository(val dataSource: FirebaseAuthApi) {
    fun logout() {
        dataSource.logout()
    }

    fun login(username: String, password: String): Task<AuthResult> {
        return dataSource.login(username, password)
    }

    fun register(username: String, password: String): Task<AuthResult> {

        return dataSource.register(username, password)
    }
}