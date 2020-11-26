package ru.geekbrains.androidhomework.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.geekbrains.androidhomework.model.entity.GithubRepo
import ru.geekbrains.androidhomework.model.entity.GithubUser

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url url: String): Single<List<GithubRepo>>
}