package com.izhangqian.checkintool.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.actors.PDDActor
import com.izhangqian.checkintool.service.MyService
import kotlinx.android.synthetic.main.activity_accessibility.*

class AccessibilityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accessibility)
        helloservice.setOnClickListener {
            var clickActor = PDDActor(MyService.getListener())
            clickActor.handleAction("111")
//            Toast.makeText(this, "you clicked!", Toast.LENGTH_SHORT).show()
        }

        fuckyou.setOnClickListener {
            Toast.makeText(this, "you clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}
