package ru.geekbrains.androidhomework.presenter

import moxy.MvpPresenter
import ru.geekbrains.androidhomework.model.entity.GithubRepo
import ru.geekbrains.androidhomework.view.IForksView
import ru.terrakok.cicerone.Router

class ForksPresenter(private val repo: GithubRepo, private val router: Router): MvpPresenter<IForksView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo.forks?.let { viewState.setForksQuantity(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}