package com.izhangqian.checkintool.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.utils.Constants
import kotlinx.android.synthetic.main.activity_checkin_detail.*

class CheckinDetailActivity : AppCompatActivity() {
    var mCmdId : String = ""
    var mCmdName : String = ""
    var mCmdPack : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin_detail)
        initData()
        initView()
    }

    private fun initData() {
        var checkinMainBean = intent.getParcelableExtra<CheckinMainBean>(Constants.EXTRA_CMD_BEAN)
        if (checkinMainBean != null) {
            mCmdId = checkinMainBean.cmdId
            mCmdName = checkinMainBean.cmdName
            mCmdPack = checkinMainBean.cmdpack
        }
    }

    private fun initView() {
        check_in_detail_name_et.setText(this.mCmdName)
        check_in_detail_pack_et.setText(this.mCmdPack)
    }
}