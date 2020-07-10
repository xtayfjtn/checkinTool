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
import com.izhangqian.checkintool.bean.checkin.CheckinCommond
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.service.MyService
import com.izhangqian.checkintool.sqlite.CheckinItemDbManager
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
//            var clickActor = PDDActor(MyService.getListener())
//            clickActor.handleAction("111")
            var cmd1 = CheckinCommond()
            cmd1.cmdStep = 0
            cmd1.cmdType = 1
            cmd1.cmdText = "这是第一部"
            var cmd2 = CheckinCommond()
            cmd2.cmdStep = 1
            cmd2.cmdType = 2
            cmd2.cmdText = "这是第二步"
            var checkinMainBean = CheckinMainBean()
            checkinMainBean.cmdName = "拼多读"
            checkinMainBean.cmdpack = "com.xunmeng.pinduoduo"
            checkinMainBean.cmdList.add(cmd1)
            checkinMainBean.cmdList.add(cmd2)
            CheckinItemDbManager.instance.insertCheckinItem(checkinMainBean)
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
