package com.izhangqian.checkintool

import android.app.Application
import com.izhangqian.checkintool.upgrade.CheckUpgrade

class MyApplication : Application() {

    companion object {
        var application : MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        CheckUpgrade.checkForUpdate(this)
    }
}