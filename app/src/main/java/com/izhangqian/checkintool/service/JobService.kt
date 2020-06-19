package com.izhangqian.checkintool.service

import android.app.Service
import android.content.Intent
import android.os.HandlerThread
import android.os.IBinder
import com.izhangqian.checkintool.utils.Logit

class JobService : Service() {
    val TAG : String = "JobService"

    var handlerThread : HandlerThread? = null

    override fun onCreate() {
        super.onCreate()
        Logit.i(TAG, "job on Create")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logit.i(TAG, "job service started")
        handlerThread = HandlerThread("myhandler")
        handlerThread.run {
            var root = MyService.getInstance().rootInActiveWindow
            while (root == null) {
                root = MyService.getInstance().rootInActiveWindow
                if (root != null) {
                    var node = root.findAccessibilityNodeInfosByText("Hello World!")
                    Logit.i(TAG, "node text: " + node.get(0).text)
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {

        return null
    }
}
