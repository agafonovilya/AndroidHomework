package ru.geekbrains.androidhomework.di

import dagger.Component
import ru.geekbrains.androidhomework.MainActivity
import ru.geekbrains.androidhomework.di.module.*
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.cache.RoomUserCache
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import ru.geekbrains.androidhomework.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import ru.geekbrains.androidhomework.mvp.presenter.ForksPresenter
import ru.geekbrains.androidhomework.mvp.presenter.MainPresenter
import ru.geekbrains.androidhomework.mvp.presenter.UserReposPresenter
import ru.geekbrains.androidhomework.mvp.presenter.UsersPresenter
import ru.geekbrains.androidhomework.ui.adapter.UsersRVAdapter
import ru.geekbrains.androidhomework.ui.fragments.ForksFragment
import ru.geekbrains.androidhomework.ui.fragments.UserReposFragment
import ru.geekbrains.androidhomework.ui.fragments.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        ImageModule::class,
        RepoModule::class,
        RxModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userReposPresenter: UserReposPresenter)
    fun inject(forksPresenter: ForksPresenter)

    fun inject(roomRepositoriesCache: RoomRepositoriesCache)
    fun inject(roomUserCache: RoomUserCache)

    fun inject(usersRVAdapter: UsersRVAdapter)

    fun inject(retrofitGithubUsersRepo: RetrofitGithubUsersRepo)
    fun inject(retrofitGithubRepositoriesRepo: RetrofitGithubRepositoriesRepo)
}
