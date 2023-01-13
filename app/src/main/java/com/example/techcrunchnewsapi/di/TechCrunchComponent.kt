package com.example.techcrunchnewsapi.di

import com.example.techcrunchnewsapi.ui.MainViewModel
import com.example.techcrunchnewsapi.ui.register.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface TechCrunchComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(userViewModel: UserViewModel)
}