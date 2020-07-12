package com.izhangqian.checkintool.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.izhangqian.checkintool.MyApplication
import com.izhangqian.checkintool.bean.checkin.CheckinCommond
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.utils.Logit

class CheckinItemDbManager private constructor() {
    init {
        getDb(MyApplication.application)
    }
    var mDb : SQLiteDatabase? = null
    var sqliteHelper : MySqliteHelper? = null
    companion object {
        val TAG = "ChcekinDb"
        val instance : CheckinItemDbManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CheckinItemDbManager()
        }
    }

    private fun getDb(context: Context?) {
        sqliteHelper = MySqliteHelper(context)
        mDb = sqliteHelper!!.writableDatabase
    }

    fun insertCheckinItem(checkinMainBean: CheckinMainBean) {
        var cv = ContentValues()
        cv.put(MySqliteHelper.CHECK_IN_CMD_NAME, checkinMainBean.cmdName)
        cv.put(MySqliteHelper.CHECK_IN_CMD_PACKAGE, checkinMainBean.cmdpack)
        mDb?.insert(MySqliteHelper.CHECK_IN_TABLE_NAME, "", cv)
        var cmds = checkinMainBean.cmdList
        for (commond in cmds) {
            var cmdCv = ContentValues()
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_NAME, checkinMainBean.cmdName)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_STEP, commond.cmdStep)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_TYPE, commond.cmdType)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_VIEW_ID, commond.cmdViewId)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_TEXT, commond.cmdText)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_VIEWTYPE, commond.cmdViewType)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_POSITIONX, commond.cmdPositionX)
            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_POSITIONY, commond.cmdPositionY)
            mDb?.insert(MySqliteHelper.CHECK_IN_CMD_TABLE, "", cmdCv)
        }
    }

    fun getCheckinCommonds() : MutableList<CheckinMainBean> {
        var result = mutableListOf<CheckinMainBean>()
        var cursor : Cursor? = null
        try {
            cursor = mDb?.query(MySqliteHelper.CHECK_IN_TABLE_NAME, null, "", null, "", "", "")
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    var checkinMainBean = CheckinMainBean()
                    var name = cursor.getString(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_NAME)) ?: ""
                    var pack = cursor.getString(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_PACKAGE)) ?: ""
                    checkinMainBean.cmdName = name
                    checkinMainBean.cmdpack = pack
                    checkinMainBean.cmdList = getComnondsbyName(name)
                    result.add(checkinMainBean)
                } while (cursor?.moveToNext() == true)
            }
        } catch (e : Exception) {
            Logit.e(TAG, "error: " + e.message)
        } finally {
            if (cursor != null) {
                try {
                    cursor.close()
                } catch (e : Exception) {
                    Logit.e(TAG, "error: " + e.message)
                }
            }
        }
        return result
    }

    fun getComnondsbyName(name : String) : MutableList<CheckinCommond> {
        var cmdCursor : Cursor? = null
        var result = mutableListOf<CheckinCommond>()
        try {
            cmdCursor = mDb?.query(MySqliteHelper.CHECK_IN_CMD_TABLE, null, MySqliteHelper.CHECK_IN_CMD_NAME + "=?", arrayOf(name), "", "", MySqliteHelper.CHECK_IN_CMD_STEP + "")
            if (cmdCursor != null && cmdCursor.moveToFirst()) {
                do {
                    val checkinCommond = CheckinCommond()
                    val step = cmdCursor.getInt(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_STEP))
                    val type = cmdCursor.getInt(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_TYPE))
                    var viewId = cmdCursor.getString(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_VIEW_ID))
                    var text = cmdCursor.getString(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_TEXT))
                    var desc = cmdCursor.getString(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_DESC))
                    var viewTye = cmdCursor.getString(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_VIEWTYPE))
                    var positionX = cmdCursor.getString(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_POSITIONX))
                    var positionY = cmdCursor.getString(cmdCursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_POSITIONY))
                    checkinCommond.cmdStep = step ?: 0
                    checkinCommond.cmdType = type ?: 0
                    checkinCommond.cmdViewId = viewId ?: ""
                    checkinCommond.cmdText = text ?: ""
                    checkinCommond.cmdDesc = desc ?: ""
                    checkinCommond.cmdViewType = viewTye ?: ""
                    checkinCommond.cmdPositionX = positionX ?: ""
                    checkinCommond.cmdPositionY = positionY ?: ""
                    result.add(checkinCommond)
                } while (cmdCursor.moveToNext())
            }
        } catch (e : Exception) {
            Logit.e(TAG, "error: " + e.message)
        } finally {
            try {
                if (cmdCursor != null) {
                    cmdCursor.close()
                }
            } catch (e : Exception) {

            }
        }
        return result
    }
}