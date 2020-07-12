package com.izhangqian.checkintool.actors

import com.izhangqian.checkintool.actionhandler.commonHandler.CommonClickHandler
import com.izhangqian.checkintool.actionhandler.commonHandler.CommonJumpHandler
import com.izhangqian.checkintool.actionhandler.commonHandler.CommonTouchHandler
import com.izhangqian.checkintool.actionhandler.pdd.AppleInHandler
import com.izhangqian.checkintool.actionhandler.pdd.JiaoShuiHandler
import com.izhangqian.checkintool.actorsdk.ActorExcutor
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.bean.checkin.CheckinCommond
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.listener.ServiceListener

class NormalActor (private val listener: ServiceListener) {
    companion object {
        var actorExcutor = ActorExcutor()
    }
    fun handleAction(checkinMainBean: CheckinMainBean) {

        for (checkCmd : CheckinCommond in checkinMainBean.cmdList) {
            if (checkCmd.cmdType == CheckinCommond.CMD_TYPE_JUMP) {
                actorExcutor.addTask(Runnable {
                    var jumpHandler = CommonJumpHandler(listener)
                    jumpHandler.setPackage(checkinMainBean.cmdpack)
                    jumpHandler.doAction(NodeDetailInfo())
                })
            } else if (checkCmd.cmdType == CheckinCommond.CMD_TYPE_CLICK) {
                actorExcutor.addTask(Runnable {
                    var clickHandler = CommonClickHandler(listener)
                    var nodeDetailInfo = NodeDetailInfo()
                    // TODO: 2020/7/11 此处需解析字符串
                    nodeDetailInfo.text = mutableListOf(checkCmd.cmdText)
                    clickHandler.doAction(nodeDetailInfo)
                })
            } else if (checkCmd.cmdType == CheckinCommond.CMD_TYPE_TOUCH) {
                actorExcutor.addTask(Runnable {
                    var toutchHandler = CommonTouchHandler(listener)
                    var nodeDetailInfo = NodeDetailInfo()
                    // TODO: 2020/7/12 此处需解析字符串
                    nodeDetailInfo.id = mutableListOf(checkCmd.cmdViewId)
                    nodeDetailInfo.positionX = checkCmd.cmdPositionX
                    nodeDetailInfo.positionY = checkCmd.cmdPositionY
                    toutchHandler.doAction(nodeDetailInfo)
                })
            }
        }
//        actorExcutor?.addTask(Runnable {
//            var clickHandler = CommonJumpHandler(listener)
//            clickHandler.doAction(NodeDetailInfo())
//        })
//        actorExcutor?.addTask(Runnable {
//            var clickHandler = AppleInHandler(listener)
//            clickHandler.doAction(NodeDetailInfo())
//        })
//        for (index in 1..30) {
//            actorExcutor?.addTask(Runnable {
//                var touchHandler = JiaoShuiHandler(listener)
//                touchHandler.doAction(NodeDetailInfo())
//            })
//        }
    }
}