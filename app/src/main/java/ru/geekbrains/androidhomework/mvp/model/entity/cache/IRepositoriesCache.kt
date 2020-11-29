package ru.geekbrains.androidhomework.mvp.model.entity.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser

interface IRepositoriesCache {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
    fun putRepositories(user: GithubUser, repositories: List<GithubRepository>): Completable
}