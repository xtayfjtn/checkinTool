package com.izhangqian.checkintool.functions.webload

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.izhangqian.checkintool.utils.CachHelper
import com.izhangqian.checkintool.utils.Logit

@SuppressLint("SetJavaScriptEnabled")
class RecordWebView : WebView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle : Int) : super(context, attributeSet, defStyle)
    companion object {
        val TAG = "RecordWebView"
    }
    init {
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                Logit.i(TAG, "new progress: $newProgress")
                super.onProgressChanged(view, newProgress)
            }
        }

        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                if (url.startsWith("http")) {
                    return super.shouldOverrideUrlLoading(view, request)
                } else {
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    context.startActivity(intent)
                    return true
                }
//                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val scrollY = CachHelper.get(context, url!!, 0) as Int
                view!!.scrollY = scrollY
            }
        }

        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.setSupportZoom(true)
        settings.domStorageEnabled = true
    }
}