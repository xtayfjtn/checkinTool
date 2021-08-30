package com.izhangqian.checkintool.utils

import android.content.Context
import android.content.SharedPreferences

object CachHelper {
    const val FILE_NAME = "sp_config"
    var mSpHelper : SharedPreferences? = null
    var mEditor : SharedPreferences.Editor? = null
    fun put(context : Context, key : String, value : Any) {
        if (mSpHelper == null) {
            mSpHelper = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        }
        if (mEditor == null) {
            mEditor = mSpHelper!!.edit()
        }

        checkType(key, value)
        mEditor!!.apply()
    }

    fun get(context: Context, key: String, defaultValue : Any) : Any {
        if (mSpHelper == null) {
            mSpHelper = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        }
        when(defaultValue) {
            is Int -> {
                return mSpHelper!!.getInt(key, defaultValue)
            }

            is Boolean -> {
                return mSpHelper!!.getBoolean(key, defaultValue)
            }

            is Long -> {
                return mSpHelper!!.getLong(key, defaultValue)
            }
            is Float -> {
                return mSpHelper!!.getFloat(key, defaultValue)
            }
            is String -> {
                return mSpHelper!!.getString(key, defaultValue)
            }
            is Set<*> -> {
                return mSpHelper!!.getStringSet(key, defaultValue as MutableSet<String>?)
            }
            else -> {
                return mSpHelper!!.getString(key, defaultValue.toString())
            }
        }
    }

    private fun checkType(key: String, value: Any) {
        when(value) {
            is Int -> {
                mEditor!!.putInt(key, value)
            }

            is Boolean -> {
                mEditor!!.putBoolean(key, value)
            }

            is Long -> {
                mEditor!!.putLong(key, value)
            }
            is Float -> {
                mEditor!!.putFloat(key, value)
            }
            is String -> {
                mEditor!!.putString(key, value)
            }
            is Set<*> -> {
                mEditor!!.putStringSet(key, value as MutableSet<String>?)
            }
            else -> {
                mEditor!!.putString(key, value.toString())
            }
        }
    }
}