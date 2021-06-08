package ru.geekbrains.androidhomework.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.mvp.model.entity.cache.IRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.entity.cache.IUserCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomUserCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(): IUserCache = RoomUserCache()
        .apply { App.instance.appComponent.inject(this) }

    @Singleton
    @Provides
    fun repositoriesCache(): IRepositoriesCache = RoomRepositoriesCache()
        .apply { App.instance.appComponent.inject(this) }
}
