package com.example.techcrunchnewsapi.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.techcrunchnewsapi.data.FirebaseAuthRepository

import com.example.techcrunchnewsapi.R
import com.google.firebase.auth.FirebaseUser

class UserViewModel(private val firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    private val _registerForm = MutableLiveData<UserFormState>()
    val userFormState: LiveData<UserFormState> = _registerForm

    private val _registerResult = MutableLiveData<FirebaseUser>()
    val registerResult: LiveData<FirebaseUser> = _registerResult

    private val _loginForm = MutableLiveData<UserFormState>()
    val loginFormState: LiveData<UserFormState> = _loginForm

    private val _loginResult = MutableLiveData<FirebaseUser>()
    val loginResult: LiveData<FirebaseUser> = _loginResult


    fun register(username: String, password: String, done: () -> Unit) {
        try {
            firebaseAuthRepository.register(username, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _registerResult.value = it.result.user
                    done()
                } else {
                    Log.d("abc", it.exception?.message.toString())

                }
            }
        } catch (e: Exception){
            TODO("handle exception")
        }
    }

    fun login(username: String, password: String, done: () -> Unit) {
        try {
            firebaseAuthRepository.login(username, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginResult.value = it.result.user
                    done()
                } else {
                    Log.d("abc", it.exception?.message.toString())

                }
            }
        } catch (e: Exception){
            TODO("handle exception")
        }
    }


    fun logout() {
        try {
            firebaseAuthRepository.logout()
        } catch (e: Exception) {
            TODO("handle exception")
        }
    }

    fun registerDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _registerForm.value = UserFormState(usernameError = R.string.invalid_username)
            _loginForm.value = UserFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = UserFormState(passwordError = R.string.invalid_password)
            _loginForm.value = UserFormState(passwordError = R.string.invalid_password)
        } else {
            _registerForm.value = UserFormState(isDataValid = true)
            _loginForm.value = UserFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}