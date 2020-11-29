package ru.geekbrains.androidhomework.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IForksView: MvpView {
    fun setForksQuantity(quantity: String)
}