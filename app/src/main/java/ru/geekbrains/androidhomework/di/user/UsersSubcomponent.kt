package ru.geekbrains.androidhomework.di.user

import dagger.Subcomponent
import ru.geekbrains.androidhomework.di.repository.UserRepositoriesSubcomponent
import ru.geekbrains.androidhomework.di.user.module.UserModule
import ru.geekbrains.androidhomework.mvp.presenter.UsersPresenter
import ru.geekbrains.androidhomework.ui.adapter.UsersRVAdapter

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UsersSubcomponent {
    fun userRepositoriesSubcomponent(): UserRepositoriesSubcomponent
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}
