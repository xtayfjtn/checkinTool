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
import com.izhangqian.checkintool.newdb.FunctionItemBean
import com.izhangqian.checkintool.utils.Logit
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
        mMainViewModel!!.getOnlineFunctions()
    }

    private fun initEvent() {
        test_add_btn.setOnClickListener {
            val functionItemBean = FunctionItemBean()
            functionItemBean.className = CheckChooseActivity::class.java.name
            functionItemBean.name = "This is pdd"
            mMainViewModel!!.insertOneFunction(this, functionItemBean)
        }

        mMainViewModel!!.mHomeFuntion?.observe(this, object : Observer<MutableList<FunctionItemBean>?> {
            override fun onChanged(t: MutableList<FunctionItemBean>?) {
                Logit.e(TAG, "here result: $t")
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
