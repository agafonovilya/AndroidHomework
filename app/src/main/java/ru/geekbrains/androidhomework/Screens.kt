package ru.geekbrains.androidhomework

import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
    class UserDataScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserDataFragment.newInstance(user)
    }
}
