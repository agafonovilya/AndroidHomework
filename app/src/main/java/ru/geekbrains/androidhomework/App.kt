package ru.geekbrains.androidhomework

import android.app.Application
import ru.geekbrains.androidhomework.di.AppComponent
import ru.geekbrains.androidhomework.di.DaggerAppComponent
import ru.geekbrains.androidhomework.di.repository.UserRepositoriesSubcomponent
import ru.geekbrains.androidhomework.di.user.UsersSubcomponent
import ru.geekbrains.androidhomework.di.module.AppModule


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var usersSubcomponent: UsersSubcomponent? = null
        private set
    var userRepositoriesSubcomponent: UserRepositoriesSubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also { usersSubcomponent = it }
    fun releaseUsersSubcomponent() { usersSubcomponent = null }
    fun initUserRepositoriesSubcomponent() = usersSubcomponent?.userRepositoriesSubcomponent().also { userRepositoriesSubcomponent = it }
    fun releaseUserRepositoriesSubcomponent() { userRepositoriesSubcomponent = null }
}
