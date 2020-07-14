package com.izhangqian.checkintool.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySqliteHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

    companion object {
        val DB_VERSION = 1
        val DATABASE_NAME = "zqsapk.db"
        // 首页功能数据库
        val FUNTION_TABLE_NAME = "table_function"
        val FUNCTION_TABLE_ID = "_id";
        val FUNCTION_NAME = "function_name";
        val FUNCTION_TYPE = "function_type";
        // 创建首页数据库
        val FUNCTION_DB_CREATE = "create table " + FUNTION_TABLE_NAME + "(" + FUNCTION_TABLE_ID +
                " integer primary key autoincrement, "+ FUNCTION_NAME + " text, " + FUNCTION_TYPE + " integer);"

        // 签到小工具数据库
        val CHECK_IN_TABLE_NAME = "table_checkin"
        val CHECK_IN_CMD_ID = "checkin_id"
        val CHECK_IN_CMD_NAME = "checkin_name"
        val CHECK_IN_CMD_PACKAGE = "checkin_package"
        val CHECK_IN_CMD_TABLE = "table_cmd"
        val CHECK_IN_CMD_STEP = "cmd_step"
        val CHECK_IN_CMD_TYPE = "cmd_type"
        val CHECK_IN_CMD_VIEW_ID = "cmd_view_id"
        val CHECK_IN_CMD_TEXT = "cmd_text"
        val CHECK_IN_CMD_DESC = "cmd_desc"
        val CHECK_IN_CMD_VIEWTYPE = "cmd_viewtype"
        val CHECK_IN_CMD_POSITIONX = "cmd_positionx"
        val CHECK_IN_CMD_POSITIONY = "cmd_positiony"
        // 创建签到数据库
        val CHECK_IN_DB_CREATE = "create table " + CHECK_IN_TABLE_NAME + "(" + CHECK_IN_CMD_NAME + " text, " +
                CHECK_IN_CMD_PACKAGE + " text);"
        val CHECK_IN_CMD_DB_CREATE = "create table " + CHECK_IN_CMD_TABLE + "(" + CHECK_IN_CMD_ID + " integer primary key autoincrement, " +
                CHECK_IN_CMD_NAME + " text, " + CHECK_IN_CMD_STEP + " integer, " + CHECK_IN_CMD_TYPE + " integer, " +
                CHECK_IN_CMD_VIEW_ID + " text, " + CHECK_IN_CMD_TEXT + " text, " + CHECK_IN_CMD_DESC + " text, " +
                CHECK_IN_CMD_VIEWTYPE + " text, " + CHECK_IN_CMD_POSITIONX + " text, " + CHECK_IN_CMD_POSITIONY + " text);"



    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(FUNCTION_DB_CREATE)
        db?.execSQL(CHECK_IN_DB_CREATE)
        db?.execSQL(CHECK_IN_CMD_DB_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}