package com.izhangqian.checkintool.functions.webload

import android.content.Context
import android.content.Intent
import com.izhangqian.checkintool.utils.Logit

class MyJavascriptInterface(val context: Context) {
    companion object {
        const val TAG = "JSInterface"
    }
    @android.webkit.JavascriptInterface
    fun openImage(img : String) {
        Logit.i(TAG, "image url: $img")
        val intent = Intent(context, ImageLoadActivity::class.java)
        intent.putExtra("imageUrl", img)
        context.startActivity(intent)
    }
}