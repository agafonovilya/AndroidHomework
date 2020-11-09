package ru.geekbrains.androidhomework.presenter

import android.util.Log
import moxy.MvpPresenter
import ru.geekbrains.androidhomework.Screens
import ru.geekbrains.androidhomework.view.MainView
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
