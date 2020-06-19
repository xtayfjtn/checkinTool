package com.izhangqian.checkintool.actionhandler

import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseFindTextClickHandler
import com.izhangqian.checkintool.actionhandler.abstractHandler.BaseTouchClickHandler
import com.izhangqian.checkintool.bean.NodeDetailInfo
import com.izhangqian.checkintool.listener.ServiceListener

class TouchHandler(listener: ServiceListener) : BaseTouchClickHandler(listener) {

    override fun doAction(info: NodeDetailInfo) {
        var textlist = mutableListOf<String>()
        textlist.add("bottle_default_bg")
        info.text = textlist
        super.doAction(info)
    }
}