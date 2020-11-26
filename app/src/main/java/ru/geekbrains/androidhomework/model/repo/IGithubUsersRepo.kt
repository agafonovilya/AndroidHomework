package ru.geekbrains.androidhomework.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.androidhomework.model.entity.GithubRepo
import ru.geekbrains.androidhomework.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getRepos(url: String): Single<List<GithubRepo>>
}