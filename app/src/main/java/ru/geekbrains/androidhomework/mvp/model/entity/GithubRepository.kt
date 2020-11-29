package ru.geekbrains.androidhomework.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepository (
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forks: String? = null
): Parcelable
