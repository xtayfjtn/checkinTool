package com.izhangqian.checkintool.webrequest

import android.webkit.CookieManager
import com.izhangqian.checkintool.utils.Logit
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

class WebViewCookieHandler : CookieJar {
    private val mCookieManager = CookieManager.getInstance()
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        val urlString = url.toString()
        for (cookie in cookies) {
            mCookieManager.setCookie(urlString, cookie.toString())
            Logit.i(TAG, "save $urlString cookie: ${cookie.toString()}")
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        val urlString = url.toString()
        val cookieString = mCookieManager.getCookie(urlString)
        if (cookieString != null && cookieString.isNotEmpty()) {
            val cookiesArray = cookieString.split(";")
            val cookies = mutableListOf<Cookie>()
            for (header in cookiesArray) {
                cookies.add(Cookie.parse(url, header)!!)
                Logit.i(TAG, "cookie url $url , header: $header")
            }
            return cookies
        }
        return Collections.emptyList()
    }

    companion object {
        const val TAG = "WebViewCookieHandler"
    }
}