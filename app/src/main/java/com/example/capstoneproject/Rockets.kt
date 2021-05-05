package com.example.capstoneproject

import android.os.Parcel
import android.os.Parcelable

class Rockets(
    val name: String?,
    val description: String?,
    val imageUrl: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return this.name!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rockets> {
        override fun createFromParcel(parcel: Parcel): Rockets {
            return Rockets(parcel)
        }

        override fun newArray(size: Int): Array<Rockets?> {
            return arrayOfNulls(size)
        }
    }
}