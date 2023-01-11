package com.example.techcrunchnewsapi.di

object TechCrunchInjector {
    private lateinit var instance: TechCrunchComponent

    fun init() {
        instance = DaggerTechCrunchComponent.builder().build()
    }

    fun getComponent(): TechCrunchComponent {
        if(!this::instance.isInitialized) {
            throw UninitializedPropertyAccessException("Must call init first from application class!")
        }
        return instance

    }
}