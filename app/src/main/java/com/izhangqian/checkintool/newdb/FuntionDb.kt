package com.izhangqian.checkintool.newdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FunctionItemBean::class], version = 1, exportSchema = false)
abstract class FuntionDb : RoomDatabase() {

    abstract fun getFunctionDao() : FunctionDao?

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