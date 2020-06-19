package com.izhangqian.checkintool.actionhandler

import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseFindTextClickHandler
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

class ClickHandler(listener: ServiceListener) : BaseFindTextClickHandler(listener) {

    override fun doAction(info: NodeDetailInfo) {
        var textlist = mutableListOf<String>()
        textlist.add("多多果园")
        info.text = textlist
        super.doAction(info)
    }
}