package com.izhangqian.checkintool.actionhandler.abstractHandler

import android.view.accessibility.AccessibilityNodeInfo
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

abstract class ActionHandler(listener: ServiceListener) {
    var mListener = listener
    abstract fun doAction(info : NodeDetailInfo)

    fun findNodeByText(textList : MutableList<String>?): AccessibilityNodeInfo? {
        var nodeInfo : AccessibilityNodeInfo? = null
        if (textList != null && textList.size > 0) {
            for (index in 1..10){
                val text = textList[index % textList.size]
                nodeInfo = mListener.findNodeByText(text)
                if (nodeInfo != null) {
                    break
                }
                Thread.sleep(100)
            }
        }
        return nodeInfo
    }

    fun findNodeByDesc(descList : MutableList<String>?): AccessibilityNodeInfo? {
        return null
    }

    fun findNodeById(idList : MutableList<String>?): AccessibilityNodeInfo? {
        return null
    }

    open fun performAction(nodeInfo: AccessibilityNodeInfo?) : Boolean {
        return mListener.performClick(nodeInfo)
    }

}