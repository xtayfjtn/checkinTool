package com.izhangqian.checkintool.functions.webload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.bumptech.glide.Glide
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.utils.CachHelper
import com.izhangqian.checkintool.utils.Logit
import kotlinx.android.synthetic.main.activity_web_load.*

class WebLoadActivity : AppCompatActivity() {
    companion object {
        val WEB_URL = "web_url"
        val TAG = "WebLoadActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_load)
        var url = intent.getStringExtra(WEB_URL)
        if (TextUtils.isEmpty(url)) {
            url = "http://www.baidu.com"
        }
        wb_load_view.loadUrl(url!!)
        wb_load_view.setTitlecallback {
            Logit.i(TAG, "here is finish load")
            wb_back_to_top.text = it ?: getString(R.string.wb_back_to_top)
        }
        wb_back_to_top.setOnClickListener {
            wb_load_view.scrollTo(0, 0)
        }
        wb_more_img.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.web_pop_window_layout, null)
            val popWindow = WebPopWindow(this)
            popWindow.setBackgroundDrawable(null)
            popWindow.isOutsideTouchable = true
            popWindow.contentView = view
            popWindow.width = ViewGroup.LayoutParams.WRAP_CONTENT
            popWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
            popWindow.showAsDropDown(wb_more_img)
            popWindow.isOutsideTouchable = true
        }

    }

    fun showImageView(url : String?) {
        runOnUiThread {
            photo_view_img.visibility = View.VISIBLE
            try {
                Glide.with(this).load(url).into(photo_view_img)
            } catch (e : Exception) {
                e.printStackTrace()
            }
            photo_view_img.enable()
            Logit.i(TAG, "SHOW IMAGE")
            photo_view_img.setOnClickListener {
                Logit.i(TAG, "GONE IMAGE")
                photo_view_img.visibility = View.GONE
            }
        }
    }

    override fun onPause() {
        val url = wb_load_view.url
        val scrollY = wb_load_view.scrollY
        CachHelper.put(applicationContext, url, scrollY)
        super.onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && wb_load_view.canGoBack()) {
            wb_load_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun getWebLink() : String? {
        return wb_load_view?.url
    }
}