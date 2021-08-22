package com.izhangqian.checkintool.newdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.functions.webload.beans.FavorDao

@Database(entities = [FunctionItemBean::class, FavorArticle::class], version = 1, exportSchema = false)
abstract class FuntionDb : RoomDatabase() {

    abstract fun getFunctionDao() : FunctionDao?
    abstract fun getFavorDao() : FavorDao?

    companion object {
        @Volatile private var instance : FuntionDb? = null
        fun getInstance(context: Context) : FuntionDb {
            return instance?: synchronized(this) {
                instance?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) : FuntionDb {
            return Room.databaseBuilder(context, FuntionDb::class.java, "my_db").build()
        }
    }
}