package com.kai.filmreviewapp

import android.os.Parcel
import android.os.Parcelable


data class Film(
    val name: String?,
    val synopsis: String?,
    val posterImage: String?,
    val backdropImage: String?,
    val releaseDate: String?,
    val voteAverage: String?,
    val voteCount: String?,
    val originalLanguage: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(synopsis)
        parcel.writeString(posterImage)
        parcel.writeString(backdropImage)
        parcel.writeString(releaseDate)
        parcel.writeString(voteAverage)
        parcel.writeString(voteCount)
        parcel.writeString(originalLanguage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}