package com.example.techcrunchnewsapi.ui.stateholders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.techcrunchnewsapi.data.FirebaseAuthRepository

import com.example.techcrunchnewsapi.R
import com.example.techcrunchnewsapi.ui.register.SigninResult
import com.google.firebase.auth.FirebaseUser

class UserViewModel(private val firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    private val _registerForm = MutableLiveData<UserFormState>()
    val userFormState: LiveData<UserFormState> = _registerForm

    private val _registerResult = MutableLiveData<FirebaseUser>()
    val registerResult: LiveData<FirebaseUser> = _registerResult

    private val _loginForm = MutableLiveData<UserFormState>()
    val loginFormState: LiveData<UserFormState> = _loginForm

    private val _loginResult = MutableLiveData<SigninResult>()
    val loginResult: LiveData<SigninResult> = _loginResult


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
        } catch (e: Exception) {
            TODO("handle exception")
        }
    }

    fun login(username: String, password: String, done: (SigninResult) -> Unit) {
            firebaseAuthRepository.login(username, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginResult.value = SigninResult(success = it.result.user) //it.result.user
                }

                done(SigninResult(it.result.user))
            }.addOnFailureListener {

                //TODO Fix: com.google.firebase.auth.FirebaseAuthInvalidUserException:
                // There is no user record corresponding to this identifier. The user may have been deleted.
                Log.d("abc", "login exception listener")
                _loginResult.value = SigninResult(error = 0)
                done(SigninResult(error = 0))
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