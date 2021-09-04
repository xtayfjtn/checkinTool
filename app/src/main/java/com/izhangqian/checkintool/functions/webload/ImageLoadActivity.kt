package com.izhangqian.checkintool.functions.webload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.izhangqian.checkintool.R
import kotlinx.android.synthetic.main.activity_image_load.*

class ImageLoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_load)
        val url = intent.getStringExtra("imageUrl")
        Glide.with(this).load(url).into(wb_image_show)
    }
}