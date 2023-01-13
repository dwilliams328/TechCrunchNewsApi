package com.example.techcrunchnewsapi.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techcrunchnewsapi.data.FirebaseAuthApi
import com.example.techcrunchnewsapi.data.FirebaseAuthRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class UserViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                firebaseAuthRepository = FirebaseAuthRepository(
                    dataSource = FirebaseAuthApi()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}