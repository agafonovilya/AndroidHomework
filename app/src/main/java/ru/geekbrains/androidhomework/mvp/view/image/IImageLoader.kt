package ru.geekbrains.androidhomework.mvp.view.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}