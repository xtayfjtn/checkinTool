package com.izhangqian.checkintool.newdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_function")
class FunctionItemBean {

    @PrimaryKey
    @ColumnInfo(name = "name")
    var name : String = ""

    @ColumnInfo(name = "pkg")
    var pkg : String = ""

    @ColumnInfo(name = "action")
    var action : String = ""

    @ColumnInfo(name = "className")
    var className : String = ""
    override fun toString(): String {
        return "FunctionItemBean(name='$name', pkg='$pkg', action='$action', className='$className')"
    }


}