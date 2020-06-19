package com.izhangqian.checkintool.actionhandler.abstractHandler

import android.view.accessibility.AccessibilityNodeInfo
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

open class BaseFindTextClickHandler(listener: ServiceListener) : ActionHandler(listener) {
    var mTryTime = 20
    override fun doAction(info: NodeDetailInfo) {
        var nodeInfo: AccessibilityNodeInfo? = null
        var textList = info.text
        var descs = info.desc
        nodeInfo = findNodeByText(textList)
        if (nodeInfo == null) {
            nodeInfo = findNodeByText(descs)
        }
        operateNode(nodeInfo)
    }

    open fun operateNode(nodeInfo: AccessibilityNodeInfo?) {
        mListener.performClick(nodeInfo)
    }
}