package ru.geekbrains.androidhomework.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.androidhomework.mvp.model.api.IDataSource
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.entity.cache.IUserCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.ui.network.INetworkStatus

class RetrofitGithubUsersRepo(private val api: IDataSource,
                              private val networkStatus: INetworkStatus,
                              private val userCache: IUserCache): IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap(
        fun(isOnline: Boolean): SingleSource<out List<GithubUser>>? {
            return if (isOnline) api.getUsers()
                .flatMap(
                    fun(users: List<GithubUser>): SingleSource<out List<GithubUser>>? {
                    return userCache.getUsers().flatMap{
                        userCache.putUsers(users).toSingleDefault(it)
                    }

                    }
                ) else {
                return userCache.getUsers()

            }
        }
    ).subscribeOn(Schedulers.io())
}
