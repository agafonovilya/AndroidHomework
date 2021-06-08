package ru.geekbrains.androidhomework.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.androidhomework.mvp.view.image.GlideImageLoader
import ru.geekbrains.androidhomework.mvp.view.image.IImageLoader

@Module
class ImageModule {

    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()

}