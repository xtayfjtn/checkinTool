package com.izhangqian.checkintool.bean.checkin

import android.os.Parcel
import android.os.Parcelable

class CheckinMainBean() : Parcelable {
    var cmdId : Int = 0
    var cmdpack : String = ""
    var cmdName : String = ""
    var cmdList : MutableList<CheckinCommond> = mutableListOf()

    constructor(parcel: Parcel) : this() {
        cmdId = parcel.readInt()
        cmdpack = parcel.readString() ?: ""
        cmdName = parcel.readString() ?: ""
        parcel.readList(cmdList, javaClass.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cmdId)
        parcel.writeString(cmdpack)
        parcel.writeString(cmdName)
        parcel.writeList(cmdList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CheckinMainBean> {
        override fun createFromParcel(parcel: Parcel): CheckinMainBean {
            return CheckinMainBean(parcel)
        }

        override fun newArray(size: Int): Array<CheckinMainBean?> {
            return arrayOfNulls(size)
        }
    }
}