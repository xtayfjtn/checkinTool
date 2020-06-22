package com.izhangqian.checkintool.actionhandler.pdd

import android.graphics.Rect
import android.view.accessibility.AccessibilityNodeInfo
import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseFindTextClickHandler
import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseTouchClickHandler
import com.izhangqian.checkintool.actors.AbstractActor
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

class JiaoShuiHandler(listener: ServiceListener) : BaseTouchClickHandler(listener) {

    override fun doAction(info: NodeDetailInfo) {
        var idlist = mutableListOf<String>()
        idlist.add("com.xunmeng.pinduoduo:id/tv_title")
        info.id = idlist
        info.viewType = AbstractActor.NODE_TYPE_TEXTVIEW
        super.doAction(info)
    }

    override fun performAction(nodeInfo: AccessibilityNodeInfo?): Boolean {
        if (nodeInfo != null) {
            mListener.performTouch(957F, 2047F, 50)
            Thread.sleep(2000)
            return true
        } else {
            return false
        }
    }
}