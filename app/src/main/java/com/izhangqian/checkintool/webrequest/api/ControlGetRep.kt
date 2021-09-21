package com.izhangqian.checkintool.webrequest.api

import com.izhangqian.checkintool.bean.ApkConfig
import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.newdb.FunctionItemBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call

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

    fun getNewApkConfig() : Observable<ApkConfig> {
        return WebClient.getControl().getNewAppConfig()
    }

    fun getNewApk(url : String) : Call<ResponseBody> {
        return WebClient.getControl().getNewApk(url)
    }
}