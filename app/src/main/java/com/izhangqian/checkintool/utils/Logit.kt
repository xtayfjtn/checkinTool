package com.izhangqian.checkintool.utils

import android.util.Log

/**
 * Created by xtayf on 2020/5/30.
 */
class Logit {
    companion object {
        val TAG_PRE = "zqsapk_"
        fun d(tag: String, msg : String) {
            Log.d(TAG_PRE + tag, msg)
        }

        fun i(tag : String, msg : String) {
            Log.i(TAG_PRE + tag, msg)
        }

        fun e(tag : String, msg : String) {
            Log.e(TAG_PRE + tag, msg)
        }
    }
}