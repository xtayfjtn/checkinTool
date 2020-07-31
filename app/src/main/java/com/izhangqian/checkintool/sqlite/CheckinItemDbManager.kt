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

    fun insertCheckinItem(checkinMainBean: CheckinMainBean) : Boolean {
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
        return true
    }

    fun insertorUpdateCheckinItem(checkinMainBean: CheckinMainBean) : Boolean {
        if (getCommondbyId(checkinMainBean.cmdId.toString()) != null) {
            return updateCommond(checkinMainBean)
        } else {
            return insertCheckinItem(checkinMainBean)
        }
    }

    private fun updateCommond(checkinMainBean: CheckinMainBean) : Boolean {
        var cv = ContentValues()
        cv.put(MySqliteHelper.CHECK_IN_CMD_NAME, checkinMainBean.cmdName)
        cv.put(MySqliteHelper.CHECK_IN_CMD_PACKAGE, checkinMainBean.cmdpack)
        mDb?.update(MySqliteHelper.CHECK_IN_TABLE_NAME, cv, MySqliteHelper.CHECK_IN_CMD_ID + "=?", arrayOf(checkinMainBean.cmdId.toString()))
        var cmds = checkinMainBean.cmdList
        mDb?.delete(MySqliteHelper.CHECK_IN_CMD_TABLE, MySqliteHelper.CHECK_IN_CMD_NAME + "=?", arrayOf(checkinMainBean.cmdName))
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
//            for (commond in cmds) {
//            var cmdCv = ContentValues()
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_NAME, checkinMainBean.cmdName)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_STEP, commond.cmdStep)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_TYPE, commond.cmdType)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_VIEW_ID, commond.cmdViewId)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_TEXT, commond.cmdText)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_VIEWTYPE, commond.cmdViewType)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_POSITIONX, commond.cmdPositionX)
//            cmdCv.put(MySqliteHelper.CHECK_IN_CMD_POSITIONY, commond.cmdPositionY)
//            mDb?.update(MySqliteHelper.CHECK_IN_CMD_TABLE, cmdCv, MySqliteHelper.CHECK_IN_CMD_NAME + "=? and " + MySqliteHelper.CHECK_IN_CMD_STEP + "=?", arrayOf(checkinMainBean.cmdName, commond.cmdStep.toString()))
//        }
        return true
    }

    fun getCheckinCommonds() : MutableList<CheckinMainBean> {
        var result = mutableListOf<CheckinMainBean>()
        var cursor : Cursor? = null
        try {
            cursor = mDb?.query(MySqliteHelper.CHECK_IN_TABLE_NAME, null, "", null, "", "", "")
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    var checkinMainBean = CheckinMainBean()
                    var id = cursor.getInt(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_ID))
                    var name = cursor.getString(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_NAME))
                    var pack = cursor.getString(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_PACKAGE))
                    checkinMainBean.cmdId = id
                    checkinMainBean.cmdName = name
                    checkinMainBean.cmdpack = pack
                    checkinMainBean.cmdList = getComnondsbyName(name)
                    result.add(checkinMainBean)
                } while (cursor.moveToNext())
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

    fun getCommondbyId(int: String) : CheckinMainBean? {
        var checkinMainBean : CheckinMainBean? = null
        var cursor : Cursor? = null
        try {
            cursor = mDb?.query(MySqliteHelper.CHECK_IN_TABLE_NAME, null, MySqliteHelper.CHECK_IN_CMD_ID + "=?", arrayOf(int), "", "", "")
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    checkinMainBean = CheckinMainBean()
                    var id = cursor.getInt(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_ID))
                    var name = cursor.getString(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_NAME))
                    var pack = cursor.getString(cursor.getColumnIndex(MySqliteHelper.CHECK_IN_CMD_PACKAGE))
                    checkinMainBean.cmdId = id
                    checkinMainBean.cmdName = name
                    checkinMainBean.cmdpack = pack
                    checkinMainBean.cmdList = getComnondsbyName(name)
                } while (cursor.moveToNext())
            }
        } catch (e : Exception) {
            Logit.e(TAG, "get commond error")
        } finally {
            if (cursor != null) {
                try {
                    cursor.close()
                } catch (e : Exception) {
                    Logit.e(TAG, "error: " + e.message)
                }
            }
        }
        return checkinMainBean
    }
}