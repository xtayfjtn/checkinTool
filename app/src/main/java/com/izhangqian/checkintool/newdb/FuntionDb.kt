package com.izhangqian.checkintool.newdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.functions.webload.beans.FavorDao

@Database(entities = [FunctionItemBean::class, FavorArticle::class], version = 2, exportSchema = false)
abstract class FuntionDb : RoomDatabase() {

    abstract fun getFunctionDao() : FunctionDao?
    abstract fun getFavorDao() : FavorDao?

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE t_favor ADD COLUMN owner TEXT not null")
            }

        }

        @Volatile private var instance : FuntionDb? = null
        fun getInstance(context: Context) : FuntionDb {
            return instance?: synchronized(this) {
                instance?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) : FuntionDb {
            return Room.databaseBuilder(context, FuntionDb::class.java, "my_db").addMigrations(
                MIGRATION_1_2).build()
        }
    }
}