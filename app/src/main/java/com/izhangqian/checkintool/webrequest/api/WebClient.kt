package com.izhangqian.checkintool.webrequest.api

import com.izhangqian.checkintool.webrequest.WebViewCookieHandler
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {
    private lateinit var retrofit : Retrofit
    init {
        val client = OkHttpClient.Builder().cookieJar(WebViewCookieHandler()).build()
        retrofit = Retrofit.Builder().client(client)
            .baseUrl("https://gitee.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
            .build()
    }

    fun getControl() : IGetControl {
        return retrofit.create(IGetControl::class.java)
    }
}