package com.izhangqian.checkintool.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.adapter.HomeMainAdapter
import com.izhangqian.checkintool.bean.HomeItemBean
import com.izhangqian.checkintool.sqlite.HomeItemDbManager
import com.izhangqian.checkintool.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    val TAG : String = "MainActivity"
    private var mMainViewModel : MainViewModel? = null
    private var mHomeAdapter : HomeMainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intData()
        initView()
        initEvent()
    }

    private fun intData() {
        mHomeAdapter = HomeMainAdapter(applicationContext)
        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel!!.getHomeDatas()
    }

    private fun initEvent() {
        test_add_btn.setOnClickListener {
            var homeItemBean = HomeItemBean(1)
            homeItemBean.name = "这是拼多多的实践" + System.currentTimeMillis()
            HomeItemDbManager.instance.insertHomeItem(homeItemBean)
            mMainViewModel!!.getHomeDatas()
        }
        mMainViewModel!!.mHomeData?.observe(this, object : Observer<MutableList<HomeItemBean>?> {
            override fun onChanged(t: MutableList<HomeItemBean>?) {
                if (t != null) {
                    mHomeAdapter?.updataList(t)
                }
            }
        })
    }

    @SuppressLint("WrongConstant")
    private fun initView() {
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = OrientationHelper.VERTICAL
        homerecycler.layoutManager = linearLayoutManager
        homerecycler.adapter = mHomeAdapter
    }
}
