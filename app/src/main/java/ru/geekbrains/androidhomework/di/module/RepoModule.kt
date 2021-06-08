package ru.geekbrains.androidhomework.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(): IGithubUsersRepo =
        RetrofitGithubUsersRepo().apply { App.instance.appComponent.inject(this) }

    @Singleton
    @Provides
    fun repositoriesRepo(): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo().apply { App.instance.appComponent.inject(this) }
}