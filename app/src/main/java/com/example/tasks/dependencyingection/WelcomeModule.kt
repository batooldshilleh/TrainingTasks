package com.example.tasks.dependencyingection

import dagger.Module
import dagger.Provides


@Module
class WelcomeModule {
    @Provides
    fun provideWelcomeMessage(): WelcomeMessage {
        return WelcomeMessage()
    }
}