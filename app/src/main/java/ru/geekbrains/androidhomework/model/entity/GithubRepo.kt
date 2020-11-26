package ru.geekbrains.androidhomework.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepo (
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forks: String? = null
): Parcelable
