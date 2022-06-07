package com.alkemy.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesListProvider(
@SerializedName ("results")
val movies : List<Movie>
) : Parcelable {
    constructor() : this(mutableListOf())
}
