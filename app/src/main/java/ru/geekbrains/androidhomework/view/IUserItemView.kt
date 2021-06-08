package ru.geekbrains.androidhomework.view

interface IUserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}