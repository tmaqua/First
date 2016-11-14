package com.murakami.fankami.first.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by fankami on 西暦16/11/14.
 */

data class User(val id: String,
                val name: String,
                val profileImageUrl: String) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = source.run {
                User(readString(), readString(), readString())
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(id)
            writeString(name)
            writeString(profileImageUrl)
        }
    }
}