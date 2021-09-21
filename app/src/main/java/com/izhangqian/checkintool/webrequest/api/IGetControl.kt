package com.izhangqian.checkintool.webrequest.api

import com.izhangqian.checkintool.bean.ApkConfig
import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.newdb.FunctionItemBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface IGetControl {

    @GET("sweethouse/totransport/raw/master/function.json")
    fun getFunctions() : Observable<List<FunctionItemBean>>

    @GET("sweethouse/totransport/raw/master/myfavor.json")
    fun getFavors() : Observable<List<FavorArticle>>

    @GET("sweethouse/totransport/raw/master/zlfavor.json")
    fun getZLFavors() : Observable<List<FavorArticle>>

    @GET("sweethouse/totransport/raw/master/apkupgrade/apkconfig.json")
    fun getNewAppConfig() : Observable<ApkConfig>

    @Streaming
    @GET
    @Headers("Accept-Encoding:identity")
    fun getNewApk(@Url url : String) : Call<ResponseBody>
}