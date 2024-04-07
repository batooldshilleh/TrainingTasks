package com.example.tasks.dependencyingection

import dagger.Component

@Component(modules = [WelcomeModule::class])
interface WelcomeComponent {
    fun inject(activity: DependencyIngectionActivity)
}