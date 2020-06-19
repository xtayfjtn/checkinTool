package com.izhangqian.checkintool.actors

import com.izhangqian.checkintool.actionhandler.ClickHandler
import com.izhangqian.checkintool.actionhandler.JumpHandler
import com.izhangqian.checkintool.actionhandler.TouchHandler
import com.izhangqian.checkintool.actorsdk.ActorExcutor
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

/**
 * Created by xtayf on 2020/5/31.
 */
class ClickActor constructor(private val listener: ServiceListener) {

    companion object {
        var actorExcutor : ActorExcutor? = ActorExcutor()
    }
    fun handleAction(commond : String) {
        actorExcutor?.addTask(Runnable {
            var clickHandler = JumpHandler(listener)
            var nodeDetailInfo = NodeDetailInfo()
            clickHandler.doAction(NodeDetailInfo())
        })
        actorExcutor?.addTask(Runnable {
            var clickHandler = ClickHandler(listener)
            clickHandler.doAction(NodeDetailInfo())
        })
        actorExcutor?.addTask(Runnable {
            var touchHandler = TouchHandler(listener)
            touchHandler.doAction(NodeDetailInfo())
        })
    }
//    fun performClick() {
//        var nodeInfo: AccessibilityNodeInfo? = null
//        while (nodeInfo == null) {
//            nodeInfo = listener.findNodeByText("fuck")
//            try {
//                Thread.sleep(100)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//        }
//        nodeInfo?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//    }

}