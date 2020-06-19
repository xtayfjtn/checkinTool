package com.izhangqian.checkintool.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.izhangqian.checkintool.R
import kotlinx.android.synthetic.main.activity_util.*

class UtilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_util)
        fuckme.setOnClickListener {
            Toast.makeText(this, "you clicked again!", Toast.LENGTH_SHORT).show()
        }
    }
}