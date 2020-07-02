package com.izhangqian.checkintool.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySqliteHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

    companion object {
        val DB_VERSION = 1
        val DATABASE_NAME = "zqsapk.db"
        val FUNTION_TABLE_NAME = "table_function"
        val FUNCTION_TABLE_ID = "_id";
        val FUNCTION_NAME = "function_name";
        val FUNCTION_TYPE = "function_type";
        val FUNCTION_DB_CREATE = "create table " + FUNTION_TABLE_NAME + "(" + FUNCTION_TABLE_ID +
                " integer primary key autoincrement, "+ FUNCTION_NAME + " text, " + FUNCTION_TYPE + " integer);"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(FUNCTION_DB_CREATE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}