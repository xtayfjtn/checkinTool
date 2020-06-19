package com.izhangqian.checkintool

import android.app.Application

class MyApplication : Application() {

    companion object {
        var application : MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}