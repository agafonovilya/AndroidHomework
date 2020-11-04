package ru.geekbrains.androidhomework.presenter

import ru.geekbrains.androidhomework.model.CountersModel
import ru.geekbrains.androidhomework.view.MainView

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun btn1Click() {
        val nextValue = model.next(0)
        view.setButton1Text(nextValue.toString())
    }

    fun btn2Click() {
        val nextValue = model.next(1)
        view.setButton2Text(nextValue.toString())
    }

    fun btn3Click() {
        val nextValue = model.next(2)
        view.setButton3Text(nextValue.toString())
    }
}
