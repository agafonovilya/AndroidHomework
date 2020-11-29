package ru.geekbrains.androidhomework.mvp.model.repo.retrofit

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.androidhomework.mvp.model.api.IDataSource
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.ui.network.INetworkStatus

class RetrofitGithubRepositoriesRepo(private val api: IDataSource,
                                     private val networkStatus: INetworkStatus,
                                     private val repositoriesCache: RoomRepositoriesCache) : IGithubRepositoriesRepo {

    override fun getRepos(user: GithubUser): Single<List<GithubRepository>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let(fun(url: String): @NonNull Single<List<GithubRepository>>? {
                return api.getRepos(url)
                    .flatMap(
                        fun(repositories: List<GithubRepository>): SingleSource<out List<GithubRepository>>? {
                            return repositoriesCache.putRepositories(user, repositories).toSingleDefault(repositories)
                        }
                    )
            }) ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                .subscribeOn(Schedulers.io())
        } else {
            return@flatMap repositoriesCache.getRepositories(user)
        }
    }.subscribeOn(Schedulers.io())
}
