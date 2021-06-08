package ru.geekbrains.androidhomework.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.androidhomework.navigation.Screens
import ru.geekbrains.androidhomework.mvp.view.IMainView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<IMainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
