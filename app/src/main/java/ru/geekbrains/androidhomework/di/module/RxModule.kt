package ru.geekbrains.androidhomework.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class RxModule {
    @Provides
    fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}