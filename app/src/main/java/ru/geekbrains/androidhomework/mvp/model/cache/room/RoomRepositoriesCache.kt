package ru.geekbrains.androidhomework.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.cache.IRepositoriesCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.room.RoomGithubUser

class RoomRepositoriesCache(val db: Database): IRepositoriesCache {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> {
        return Single.fromCallable {
            val roomUser: RoomGithubUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")

            val roomGithubRepository: List<RoomGithubRepository> = db.repositoryDao.findForUser(roomUser.id)

            val githubRepository: MutableList<GithubRepository> = ArrayList()

            roomGithubRepository.forEach {
                githubRepository.add(GithubRepository(it.id, it.name, it.forks))
            }

            githubRepository
        }
    }

    override fun putRepositories(user: GithubUser, repositories: List<GithubRepository>): Completable {
        return Completable.fromAction {
            val roomUser: RoomGithubUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")

            val roomGithubRepositories: MutableList<RoomGithubRepository> = ArrayList()

            repositories.forEach {
                roomGithubRepositories.add(RoomGithubRepository(it.id ?: "",
                                                                it.name ?: "",
                                                                it.forks ?: "0",
                                                                roomUser.id ))
            }

            db.repositoryDao.insert(roomGithubRepositories)
        }.subscribeOn(Schedulers.io())
    }
}