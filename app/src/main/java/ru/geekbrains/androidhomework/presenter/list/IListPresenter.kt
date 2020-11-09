package ru.geekbrains.androidhomework.presenter.list

import ru.geekbrains.androidhomework.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}