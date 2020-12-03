package ru.geekbrains.androidhomework.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}