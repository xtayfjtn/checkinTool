package com.izhangqian.checkintool.utils

import android.util.Log

/**
 * Created by xtayf on 2020/5/30.
 */
class Logit {
    companion object {
        fun d(tag: String, msg : String) {
            Log.d(tag, msg)
        }

        fun i(tag : String, msg : String) {
            Log.i(tag, msg)
        }

        fun e(tag : String, msg : String) {
            Log.e(tag, msg)
        }
    }
}