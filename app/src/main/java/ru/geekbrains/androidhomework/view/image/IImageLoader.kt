package ru.geekbrains.androidhomework.view.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}