package ru.geekbrains.androidhomework.di

import dagger.Component
import ru.geekbrains.androidhomework.ui.activity.MainActivity
import ru.geekbrains.androidhomework.di.module.*
import ru.geekbrains.androidhomework.di.user.UsersSubcomponent
import ru.geekbrains.androidhomework.mvp.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun usersSubcomponent() : UsersSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}
