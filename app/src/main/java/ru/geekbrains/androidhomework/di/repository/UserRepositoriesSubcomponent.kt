package ru.geekbrains.androidhomework.di.repository

import dagger.Subcomponent
import ru.geekbrains.androidhomework.di.repository.module.RepositoryModule
import ru.geekbrains.androidhomework.mvp.presenter.ForksPresenter
import ru.geekbrains.androidhomework.mvp.presenter.UserRepositoriesPresenter

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface UserRepositoriesSubcomponent {
    fun inject(userPresenter: UserRepositoriesPresenter)
    fun inject(forksPresenter: ForksPresenter)
}