package com.izhangqian.checkintool.webrequest.api

import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.newdb.FunctionItemBean
import io.reactivex.Observable
import retrofit2.http.GET

interface IGetControl {

    @GET("sweethouse/totransport/raw/master/function.json")
    fun getFunctions() : Observable<List<FunctionItemBean>>

    @GET("sweethouse/totransport/raw/master/myfavor.json")
    fun getFavors() : Observable<List<FavorArticle>>

    @GET("sweethouse/totransport/raw/master/zlfavor.json")
    fun getZLFavors() : Observable<List<FavorArticle>>
}