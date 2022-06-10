package com.alkemy.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String ?,
    @SerializedName("title")
    val title: String ?,
    @SerializedName("poster_path")
    val poster_path: String ?,
    @SerializedName("release_date")
    val release_date: String ?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val vote_average: Double?

) : Parcelable {
    constructor() : this("","","","","",null)
}