package ru.geekbrains.androidhomework

import android.app.Application
import ru.geekbrains.androidhomework.di.AppComponent
import ru.geekbrains.androidhomework.di.DaggerAppComponent
import ru.geekbrains.androidhomework.di.module.AppModule


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}
