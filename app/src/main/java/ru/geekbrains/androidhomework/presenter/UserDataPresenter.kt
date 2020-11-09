package ru.geekbrains.androidhomework.presenter

import moxy.MvpPresenter

import ru.geekbrains.androidhomework.model.entity.GithubUser
import ru.geekbrains.androidhomework.view.UserDataView
import ru.terrakok.cicerone.Router


class UserDataPresenter(val user : GithubUser, val router : Router) : MvpPresenter<UserDataView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backPressed(): Boolean {
        router.backTo(null)
        return true
    }
}
