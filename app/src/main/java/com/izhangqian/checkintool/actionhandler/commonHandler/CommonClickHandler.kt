package com.izhangqian.checkintool.actionhandler.commonHandler

import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseFindTextClickHandler
import com.izhangqian.checkintool.actors.AbstractActor
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

class CommonClickHandler(listener: ServiceListener) : BaseFindTextClickHandler(listener) {

    override fun doAction(info: NodeDetailInfo) {
//        var textlist = mutableListOf<String>()
//        textlist.add("多多果园")
//        info.text = textlist
        info.viewType = AbstractActor.NODE_TYPE_TEXTVIEW
        super.doAction(info)
    }
}