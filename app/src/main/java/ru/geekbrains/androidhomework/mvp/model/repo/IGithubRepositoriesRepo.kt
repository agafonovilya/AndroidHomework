package ru.geekbrains.androidhomework.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser

interface IGithubRepositoriesRepo {
    fun getRepos(user: GithubUser): Single<List<GithubRepository>>
}