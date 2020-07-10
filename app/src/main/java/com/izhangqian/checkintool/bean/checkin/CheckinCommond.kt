package com.izhangqian.checkintool.bean.checkin

import android.os.Parcel
import android.os.Parcelable

class CheckinCommond() : Parcelable {
    var cmdName : String = ""
    var cmdStep : Int = 0
    var cmdType : Int = 0
    var cmdText : String = ""
    var cmdDesc : String = ""
    var cmdViewType : String = ""
    var cmdPositionX : String = ""
    var cmdPositionY : String = ""

    constructor(parcel: Parcel) : this() {
        cmdName = parcel.readString() ?: ""
        cmdStep = parcel.readInt()
        cmdType = parcel.readInt()
        cmdText = parcel.readString() ?: ""
        cmdDesc = parcel.readString() ?: ""
        cmdViewType = parcel.readString() ?: ""
        cmdPositionX = parcel.readString() ?: ""
        cmdPositionY = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cmdName)
        parcel.writeInt(cmdStep)
        parcel.writeInt(cmdType)
        parcel.writeString(cmdText)
        parcel.writeString(cmdDesc)
        parcel.writeString(cmdViewType)
        parcel.writeString(cmdPositionX)
        parcel.writeString(cmdPositionY)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CheckinCommond> {
        override fun createFromParcel(parcel: Parcel): CheckinCommond {
            return CheckinCommond(parcel)
        }

        override fun newArray(size: Int): Array<CheckinCommond?> {
            return arrayOfNulls(size)
        }
    }
}