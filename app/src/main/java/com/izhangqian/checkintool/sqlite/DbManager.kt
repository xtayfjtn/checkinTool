package com.izhangqian.checkintool.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.izhangqian.checkintool.MyApplication
import com.izhangqian.checkintool.bean.HomeItemBean
import com.izhangqian.checkintool.utils.Logit
import kotlin.reflect.KProperty

class DbManager private constructor() {
    init {
        getDb(MyApplication.application)
    }
    var mDb : SQLiteDatabase? = null
    var sqliteHelper : MySqliteHelper? = null
    companion object {
        val instance : DbManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DbManager()
        }
    }
    private fun getDb(context: Context?) {
        sqliteHelper = MySqliteHelper(context)
        mDb = sqliteHelper!!.writableDatabase
    }

    fun insertHomeItem(homeItemBean: HomeItemBean) {
        var cv = ContentValues()
        cv.put(MySqliteHelper.FUNCTION_NAME, homeItemBean.name)
        cv.put(MySqliteHelper.FUNCTION_TYPE, homeItemBean.itemType)
        mDb?.insert(MySqliteHelper.FUNTION_TABLE_NAME, "", cv)
    }

    fun getHomeItems() : MutableList<HomeItemBean> {
        var cursor = mDb?.query(MySqliteHelper.FUNTION_TABLE_NAME, null, "", null, "", "", "")
        var homeItems = mutableListOf<HomeItemBean>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var name = cursor.getString(cursor.getColumnIndex(MySqliteHelper.FUNCTION_NAME))
                var type = cursor.getInt(cursor.getColumnIndex(MySqliteHelper.FUNCTION_TYPE))
                var homeItemBean = HomeItemBean(type)
                homeItemBean.name = name
                homeItems.add(homeItemBean)
                Logit.i("what", "ddd" + cursor.getString(cursor.getColumnIndex(MySqliteHelper.FUNCTION_NAME)))

            } while (cursor.moveToNext())
        }
        return homeItems
    }
}
