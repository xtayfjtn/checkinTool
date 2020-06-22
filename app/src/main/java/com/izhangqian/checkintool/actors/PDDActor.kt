package com.izhangqian.checkintool.actors

import com.izhangqian.checkintool.actionhandler.pdd.AppleInHandler
import com.izhangqian.checkintool.actionhandler.pdd.JumpHandler
import com.izhangqian.checkintool.actionhandler.pdd.JiaoShuiHandler
import com.izhangqian.checkintool.actorsdk.ActorExcutor
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

/**
 * Created by xtayf on 2020/5/31.
 */
class PDDActor constructor(private val listener: ServiceListener) {

    companion object {
        var actorExcutor : ActorExcutor? = ActorExcutor()
    }
    fun handleAction(commond : String) {
        actorExcutor?.addTask(Runnable {
            var clickHandler = JumpHandler(listener)
            clickHandler.doAction(NodeDetailInfo())
        })
        actorExcutor?.addTask(Runnable {
            var clickHandler = AppleInHandler(listener)
            clickHandler.doAction(NodeDetailInfo())
        })
        for (index in 1..30) {
            actorExcutor?.addTask(Runnable {
                var touchHandler = JiaoShuiHandler(listener)
                touchHandler.doAction(NodeDetailInfo())
            })
        }
    }
}