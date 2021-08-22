package com.izhangqian.checkintool.functions.webload.beans

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_favor")
class FavorArticle {
    @ColumnInfo(name = "type")
    var type = ""
    @ColumnInfo(name = "title")
    var title = ""
    @PrimaryKey
    @ColumnInfo(name = "url")
    var url = ""
    @ColumnInfo(name = "imgurl")
    var imgurl = ""
    override fun toString(): String {
        return "FavorArticle(type='$type', title='$title', url='$url', imgurl='$imgurl')"
    }

}