package com.izhangqian.checkintool.actionhandler.commonHandler

import android.content.Context
import android.content.Intent
import com.izhangqian.checkintool.actionhandler.abstractHandler.ActionHandler
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

class CommonJumpHandler(listener: ServiceListener) : ActionHandler(listener) {

    var packagePdd = "com.xunmeng.pinduoduo"
    val activityName = "com.xunmeng.pinduoduo.ui.activity.HomeActivity"
    override fun doAction(info: NodeDetailInfo) {
        if (mListener is Context) {
            var context = (mListener as Context)
            var intent = context.packageManager.getLaunchIntentForPackage(packagePdd)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            (mListener as Context).startActivity(intent)
        }
    }

    fun setPackage(pack : String) {
        packagePdd = pack
    }
}