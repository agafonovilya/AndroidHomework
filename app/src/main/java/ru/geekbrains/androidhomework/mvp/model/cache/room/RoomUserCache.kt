package ru.geekbrains.androidhomework.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.cache.IUserCache
import ru.geekbrains.androidhomework.mvp.model.entity.room.Database
import ru.geekbrains.androidhomework.mvp.model.entity.room.RoomGithubUser

class RoomUserCache(val db: Database): IUserCache {

    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
            val roomGithubUser : List<RoomGithubUser> = db.userDao.getAll()
            val users: MutableList<GithubUser> = ArrayList()

            roomGithubUser.forEach {
                users.add(GithubUser(it.id, it.login, it.avatarUrl, it.reposUrl))
            }

            return@fromCallable users

        }
    }

    override fun putUsers(users: List<GithubUser>): Completable {
        return Completable.fromAction{
            val roomGithubUser: MutableList<RoomGithubUser> = ArrayList()

            users.forEach {
                roomGithubUser.add(RoomGithubUser(it.id ?: "",
                                                it.login ?: "",
                                                it.avatarUrl?: "",
                                                it.reposUrl?: ""))
            }

            db.userDao.insert(roomGithubUser)
        }.subscribeOn(Schedulers.io())
    }
}