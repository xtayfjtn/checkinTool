package com.izhangqian.checkintool.actionhandler.commonHandler

import android.view.accessibility.AccessibilityNodeInfo
import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseTouchClickHandler
import com.izhangqian.checkintool.actors.AbstractActor
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

class CommonTouchHandler(listener: ServiceListener) : BaseTouchClickHandler(listener) {
    var nodeDetal : NodeDetailInfo? = null
    override fun doAction(info: NodeDetailInfo) {
        nodeDetal = info
        info.id = nodeDetal?.id
        info.viewType = AbstractActor.NODE_TYPE_TEXTVIEW
        super.doAction(info)
    }

    override fun performAction(nodeInfo: AccessibilityNodeInfo?): Boolean {
        try {
            var x : Float = nodeDetal?.positionX?.toFloat() ?: 0F
            var y : Float = nodeDetal?.positionY?.toFloat() ?: 0F
            mListener.performTouch(x, y, 50)
            Thread.sleep(2000)
        } catch (e : Exception) {

        }
        return true
    }
}