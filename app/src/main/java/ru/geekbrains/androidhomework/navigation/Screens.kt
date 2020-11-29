package ru.geekbrains.androidhomework.navigation

import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.model.entity.GithubUser
import ru.geekbrains.androidhomework.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.androidhomework.ui.fragments.ForksFragment
import ru.geekbrains.androidhomework.ui.fragments.UserReposFragment
import ru.geekbrains.androidhomework.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
    class UserReposScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserReposFragment.newInstance(user)
    }

    class ForksScreen(private val repo: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = ForksFragment.newInstance(repo)
    }
}
