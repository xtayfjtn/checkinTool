package com.izhangqian.checkintool.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.actors.PDDActor
import com.izhangqian.checkintool.adapter.CheckinListAdapter
import com.izhangqian.checkintool.service.MyService
import com.izhangqian.checkintool.viewmodel.CheckChooseViewModel
import kotlinx.android.synthetic.main.activity_accessibility.*

class CheckChooseActivity : AppCompatActivity() {

    var mCheckModel : CheckChooseViewModel? = null
    var mCheckinListAdapter : CheckinListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accessibility)
        initData()
        initView()
        initEvent()
        helloservice.setOnClickListener {
            var clickActor = PDDActor(MyService.getListener())
            clickActor.handleAction("111")
        }
    }

    private fun initData() {
        mCheckModel = ViewModelProvider(this).get(CheckChooseViewModel::class.java)
        mCheckModel?.getCheckInData()
    }

    private fun initView() {
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        checkin_rv.layoutManager = linearLayoutManager
        mCheckinListAdapter = CheckinListAdapter(this)
        checkin_rv.adapter = mCheckinListAdapter
    }

    private fun initEvent() {
        mCheckModel?.mCheckinMainBean?.observe({
            lifecycle
        }, {
            mCheckinListAdapter?.updateData(it)
        })
    }
}
