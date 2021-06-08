package ru.geekbrains.androidhomework.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.androidhomework.mvp.model.api.IDataSource
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.cache.IRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.network.INetworkStatus

class RetrofitGithubRepositoriesRepo(val api: IDataSource,
                                     val networkStatus: INetworkStatus,
                                     val cache: IRepositoriesCache): IGithubRepositoriesRepo {

    override fun getRepos(user: GithubUser): Single<List<GithubRepository>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getRepos(url)
                    .flatMap { repositories ->
                        cache.putRepositories(user, repositories).toSingleDefault(repositories)
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            cache.getRepositories(user)
        }
    }.subscribeOn(Schedulers.io())
}
