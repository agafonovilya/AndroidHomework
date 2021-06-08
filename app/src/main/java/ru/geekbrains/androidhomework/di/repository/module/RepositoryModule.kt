package ru.geekbrains.androidhomework.di.repository.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.di.repository.RepositoryScope
import ru.geekbrains.androidhomework.mvp.model.api.IDataSource
import ru.geekbrains.androidhomework.mvp.model.cache.IRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.cache.room.RoomRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.network.INetworkStatus

@Module
open class RepositoryModule {
    @Provides
    fun repositoriesCache(database: Database): IRepositoriesCache {
        return RoomRepositoriesCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource, networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubRepositoriesRepo {
        return RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
    }
}