package com.izhangqian.checkintool.functions.webload

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import com.izhangqian.checkintool.R

class WebPopWindow(val context: Context?) : PopupWindow(context) {

    var copyLinkTv : TextView? = null
    var openlink : TextView? = null
    var close_web : TextView? = null
    override fun setContentView(contentView: View?) {
        super.setContentView(contentView)
        copyLinkTv = contentView?.findViewById(R.id.copy_link)
        openlink = contentView?.findViewById(R.id.open_in_web)
        close_web = contentView?.findViewById(R.id.close_web)
        copyLinkTv?.setOnClickListener {
            val clipManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            if (context is WebLoadActivity) {
                val clipData = ClipData.newPlainText("Label", context.getWebLink())
                clipManager.primaryClip = clipData
            }
        }

        openlink?.setOnClickListener {
            if (context is WebLoadActivity) {
                val url = Uri.parse(context.getWebLink())
                val intent = Intent(Intent.ACTION_VIEW, url)
                context.startActivity(intent)
            }

        }
        close_web?.setOnClickListener {
            if (context is WebLoadActivity) {
                context.finish()
            }
        }
    }
}