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

        test_add_btn.setOnClickListener {
//            var clickActor = PDDActor(MyService.getListener())
//            clickActor.handleAction("111")
            var cmd1 = CheckinCommond()
            cmd1.cmdStep = 0
            cmd1.cmdType = CheckinCommond.CMD_TYPE_JUMP
            cmd1.cmdText = "这是第一部"
            var cmd2 = CheckinCommond()
            cmd2.cmdStep = 1
            cmd2.cmdType = CheckinCommond.CMD_TYPE_CLICK
            cmd2.cmdText = "多多果园"
            var cmd3 = CheckinCommond()
            cmd3.cmdStep = 2
            cmd3.cmdType = CheckinCommond.CMD_TYPE_TOUCH
            cmd3.cmdViewId = "com.xunmeng.pinduoduo:id/tv_title"
            cmd3.cmdPositionX = "957"
            cmd3.cmdPositionY = "2047"
            var checkinMainBean = CheckinMainBean()
            checkinMainBean.cmdName = "拼多读" + System.currentTimeMillis()
            checkinMainBean.cmdpack = "com.xunmeng.pinduoduo"
            checkinMainBean.cmdList.add(cmd1)
            checkinMainBean.cmdList.add(cmd2)
            checkinMainBean.cmdList.add(cmd3)
            for (index in 1..10) {
                var cmd4 = CheckinCommond()
                cmd4.cmdStep = index + 2
                cmd4.cmdType = CheckinCommond.CMD_TYPE_TOUCH
                cmd4.cmdViewId = "com.xunmeng.pinduoduo:id/tv_title"
                cmd4.cmdPositionX = "957"
                cmd4.cmdPositionY = "2047"
                checkinMainBean.cmdList.add(cmd4)
            }
            CheckinItemDbManager.instance.insertCheckinItem(checkinMainBean)
        }
    }

    private fun initEvent() {
        mCheckModel?.mCheckinMainBean?.observe({
            lifecycle
        }, {
            mCheckinListAdapter?.updateData(it)
        })
    }
}
