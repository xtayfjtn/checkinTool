package com.izhangqian.checkintool.webrequest.api

import com.izhangqian.checkintool.newdb.FunctionItemBean
import io.reactivex.Observable

object ControlGetRep {
    fun getControlFunction() : Observable<List<FunctionItemBean>> {
        return WebClient.getControl().getFunctions()
    }
}