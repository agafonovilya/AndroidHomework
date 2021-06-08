package ru.geekbrains.androidhomework.di.user.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.di.user.UserScope
import ru.geekbrains.androidhomework.mvp.model.api.IDataSource
import ru.geekbrains.androidhomework.mvp.model.cache.IUserCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.cache.room.RoomUserCache
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.model.network.INetworkStatus

@Module
open class UserModule {
    @Provides
    fun usersCache(database: Database): IUserCache {
        return RoomUserCache(database)
    }
    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: INetworkStatus,
                       cache: IUserCache
    ): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus, cache)
    }
}
