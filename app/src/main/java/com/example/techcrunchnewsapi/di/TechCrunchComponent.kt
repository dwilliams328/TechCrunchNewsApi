package com.example.techcrunchnewsapi.di

import com.example.techcrunchnewsapi.ui.stateholders.MainViewModel
import com.example.techcrunchnewsapi.ui.stateholders.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface TechCrunchComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(userViewModel: UserViewModel)
}