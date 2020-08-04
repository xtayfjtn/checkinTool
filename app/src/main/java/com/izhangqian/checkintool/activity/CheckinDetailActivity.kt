package com.izhangqian.checkintool.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.adapter.CommondListAdapter
import com.izhangqian.checkintool.bean.checkin.CheckinCommond
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.sqlite.CheckinItemDbManager
import com.izhangqian.checkintool.utils.Constants
import com.izhangqian.checkintool.viewmodel.CheckCmdDetailViewModel
import kotlinx.android.synthetic.main.activity_checkin_detail.*
import kotlin.toString as toString1

class CheckinDetailActivity : AppCompatActivity() {
    var mCheckinMainBean : CheckinMainBean? = null
    var mCheckCmdDetailViewModel : CheckCmdDetailViewModel? = null
    var commondListAdapter : CommondListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin_detail)
        initData()
        initView()
        initEvent()
    }

    private fun initData() {
        mCheckinMainBean = intent.getParcelableExtra<CheckinMainBean>(Constants.EXTRA_CMD_BEAN)
        mCheckCmdDetailViewModel = ViewModelProvider(this).get(CheckCmdDetailViewModel::class.java)
        mCheckCmdDetailViewModel?.getCheckCmdDetailByid(mCheckinMainBean?.cmdId.toString1())
    }

    private fun initView() {
        check_in_detail_name_et.setText(mCheckinMainBean?.cmdName)
        check_in_detail_pack_et.setText(mCheckinMainBean?.cmdpack)
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        check_in_cmd_rv.layoutManager = linearLayoutManager
        commondListAdapter = CommondListAdapter(this)
        commondListAdapter?.setAdapterEvent(object : CommondListAdapter.AdapterListener {
            override fun updateDatabase() {
                mCheckinMainBean?.let { it1 -> CheckinItemDbManager.instance.insertorUpdateCheckinItem(it1) }
            }
        })
        check_in_cmd_rv.adapter = commondListAdapter
    }

    private fun initEvent() {
        cmd_submit_btn.setOnClickListener {
            mCheckinMainBean?.let {
                it1 -> CheckinItemDbManager.instance.insertorUpdateCheckinItem(it1)
                Toast.makeText(this, "update databases success", Toast.LENGTH_SHORT).show()
            }
        }

        cmd_add_btn.setOnClickListener {
            mCheckinMainBean?.cmdList?.add(CheckinCommond())
            mCheckinMainBean?.cmdList?.let { it1 -> commondListAdapter?.updateData(it1) }
        }

        mCheckCmdDetailViewModel?.mCheckCmdDetal?.observe({ lifecycle }, {
            if (it != null) {
                mCheckinMainBean = it
                mCheckinMainBean?.cmdList?.let { it1 -> commondListAdapter?.updateData(it1) }
            }
        })

        check_in_detail_pack_et.addTextChangedListener(object : CommondListAdapter.MyTextWatch() {
            override fun afterTextChanged(s: Editable?) {
                mCheckinMainBean?.cmdpack = s.toString1()
            }
        })

        check_in_detail_name_et.addTextChangedListener(object : CommondListAdapter.MyTextWatch() {
            override fun afterTextChanged(s: Editable?) {
                mCheckinMainBean?.cmdName = s.toString1()
            }
        })
    }
}