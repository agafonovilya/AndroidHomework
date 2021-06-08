package ru.geekbrains.androidhomework.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.androidhomework.navigation.Screens
import ru.geekbrains.androidhomework.mvp.view.IMainView
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
