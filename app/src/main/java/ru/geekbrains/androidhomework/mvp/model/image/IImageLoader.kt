package ru.geekbrains.androidhomework.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}