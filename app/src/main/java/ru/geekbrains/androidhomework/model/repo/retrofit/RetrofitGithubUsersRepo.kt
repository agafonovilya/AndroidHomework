package ru.geekbrains.androidhomework.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.androidhomework.model.api.IDataSource
import ru.geekbrains.androidhomework.model.entity.GithubRepo
import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(private val api: IDataSource): IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())
    override fun getRepos(url: String): Single<List<GithubRepo>> = api.getRepos(url).subscribeOn(Schedulers.io())
}
