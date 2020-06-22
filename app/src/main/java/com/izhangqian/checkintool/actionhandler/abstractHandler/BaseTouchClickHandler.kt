package com.izhangqian.checkintool.actionhandler.abstractHandler

import android.graphics.Rect
import android.view.accessibility.AccessibilityNodeInfo
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

open class BaseTouchClickHandler(listener: ServiceListener) : ActionHandler(listener) {
    override fun doAction(info: NodeDetailInfo) {
        super.doAction(info)
        var nodeInfo : AccessibilityNodeInfo? = null
        if (info.id != null) {
            nodeInfo = findNodeById(info.id)
        } else if (info.text != null) {
            nodeInfo = findNodeByText(info.text);
        } else if (info.desc != null) {
            nodeInfo = findNodeByDesc(info.desc);
        }
        performAction(nodeInfo)
    }

    override fun performAction(nodeInfo: AccessibilityNodeInfo?): Boolean {
        if (nodeInfo != null) {
            var rect = Rect()
            nodeInfo.getBoundsInScreen(rect)
            mListener.performTouch(rect.centerX().toFloat(), rect.centerY().toFloat(), 50)
        }
        return true
    }
}