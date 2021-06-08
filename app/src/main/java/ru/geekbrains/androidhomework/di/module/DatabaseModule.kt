package ru.geekbrains.androidhomework.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.App
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
}
