package com.izhangqian.checkintool.functions.webload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import com.izhangqian.checkintool.R
import kotlinx.android.synthetic.main.activity_web_load.*

class WebLoadActivity : AppCompatActivity() {
    companion object {
        val WEB_URL = "web_url"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_load)
        var url = intent.getStringExtra(WEB_URL)
        if (TextUtils.isEmpty(url)) {
            url = "http://www.baidu.com"
        }
        wb_load_view.loadUrl(url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && wb_load_view.canGoBack()) {
            wb_load_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}