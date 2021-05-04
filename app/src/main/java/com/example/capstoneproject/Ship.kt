package com.example.capstoneproject

import android.os.Parcel
import android.os.Parcelable

class Ship(
    val name: String?,
    val imageUrl: String?,
    val homePort: String?,
    val yearBuilt: Int,
    val type: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return this.name!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(homePort)
        parcel.writeInt(yearBuilt)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ship> {
        override fun createFromParcel(parcel: Parcel): Ship {
            return Ship(parcel)
        }

        override fun newArray(size: Int): Array<Ship?> {
            return arrayOfNulls(size)
        }
    }

}