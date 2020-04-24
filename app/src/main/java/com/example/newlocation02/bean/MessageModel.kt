package com.example.newlocation02.bean

import android.os.Parcel
import android.os.Parcelable

class MessageModel(private var from: String?,
                   private var to: String?,
                   private var content: String?
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MessageModel> = object : Parcelable.Creator<MessageModel> {
            override fun createFromParcel(source: Parcel): MessageModel = MessageModel(source)
            override fun newArray(size: Int): Array<MessageModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(from)
        writeString(to)
        writeString(content)
    }
}