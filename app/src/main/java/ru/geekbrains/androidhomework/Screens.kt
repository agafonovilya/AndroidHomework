package ru.geekbrains.androidhomework

import ru.geekbrains.androidhomework.model.entity.GithubRepo
import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.model.repo.IGithubUsersRepo
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
    class UserReposScreen(private val user: GithubUser, private val usersRepo: IGithubUsersRepo) : SupportAppScreen() {
        override fun getFragment() = UserReposFragment.newInstance(user, usersRepo)
    }

    class ForksScreen(private val repo: GithubRepo) : SupportAppScreen() {
        override fun getFragment() = ForksFragment.newInstance(repo)
    }
}
