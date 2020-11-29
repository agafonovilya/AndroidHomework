package ru.geekbrains.androidhomework.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.androidhomework.mvp.model.entity.GithubRepository
import ru.geekbrains.androidhomework.mvp.view.IForksView
import ru.terrakok.cicerone.Router

class ForksPresenter(private val repo: GithubRepository, private val router: Router): MvpPresenter<IForksView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo.forks?.let { viewState.setForksQuantity(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}