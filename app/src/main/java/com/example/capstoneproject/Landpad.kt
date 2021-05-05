package com.example.capstoneproject

import android.os.Parcel
import android.os.Parcelable

class Landpad(
    val name: String?,
    val details: String?,
    val type: String?,
    val locality: String?,
    val region: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(details)
        parcel.writeString(type)
        parcel.writeString(locality)
        parcel.writeString(region)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Landpad> {
        override fun createFromParcel(parcel: Parcel): Landpad {
            return Landpad(parcel)
        }

        override fun newArray(size: Int): Array<Landpad?> {
            return arrayOfNulls(size)
        }
    }

}