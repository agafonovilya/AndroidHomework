package ru.geekbrains.androidhomework.presenter

import moxy.MvpPresenter
import ru.geekbrains.androidhomework.Screens
import ru.geekbrains.androidhomework.view.IMainView
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
