package ru.geekbrains.androidhomework.mvp.presenter.list

import ru.geekbrains.androidhomework.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}