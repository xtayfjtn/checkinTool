package com.izhangqian.checkintool.webrequest.api

import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.newdb.FunctionItemBean
import io.reactivex.Observable

object ControlGetRep {
    fun getControlFunction() : Observable<List<FunctionItemBean>> {
        return WebClient.getControl().getFunctions()
    }

    fun getFavors() : Observable<List<FavorArticle>> {
        return WebClient.getControl().getFavors()
    }

    fun getZLFavors() : Observable<List<FavorArticle>> {
        return WebClient.getControl().getZLFavors()
    }
}