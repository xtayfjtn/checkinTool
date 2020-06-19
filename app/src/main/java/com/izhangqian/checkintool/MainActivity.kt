package com.izhangqian.checkintool

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.izhangqian.checkintool.adapter.HomeMainAdapter
import com.izhangqian.checkintool.bean.HomeItemBean
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
    }

    private fun intData() {
        mHomeAdapter = HomeMainAdapter(applicationContext)
        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel!!.mHomeData?.observe(this, object : Observer<List<HomeItemBean>?> {
            override fun onChanged(t: List<HomeItemBean>?) {
                if (t != null) {
                    for (homeItem : HomeItemBean in t) {
                        mHomeAdapter?.addItem(homeItem)
                    }
                }
            }
        })

        mMainViewModel!!.getHomeDatas()
    }

    @SuppressLint("WrongConstant")
    private fun initView() {
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = OrientationHelper.VERTICAL
        homerecycler.layoutManager = linearLayoutManager
        homerecycler.adapter = mHomeAdapter
    }
}
