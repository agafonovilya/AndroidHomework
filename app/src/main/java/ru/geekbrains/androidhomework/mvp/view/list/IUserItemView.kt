package ru.geekbrains.androidhomework.mvp.view.list

interface IUserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}