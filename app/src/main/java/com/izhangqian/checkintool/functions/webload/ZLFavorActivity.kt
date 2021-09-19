package com.izhangqian.checkintool.functions.webload

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.utils.Logit
import kotlinx.android.synthetic.main.activity_zlfavor.*

class ZLFavorActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ZLFavorActivity";
    }
    private var mFavorModel : FavorViewModel? = null
    private var mAdpater : FavorAdapter? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zlfavor)
        mAdpater = FavorAdapter(this)
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = OrientationHelper.VERTICAL
        zl_favor_rv.layoutManager = linearLayoutManager
        zl_favor_rv.adapter = mAdpater

        mFavorModel = ViewModelProvider(this).get(FavorViewModel::class.java)

        mFavorModel!!.getZLWebFavor()

        mFavorModel!!.mZLFavorList?.observe(this, Observer {
            Logit.i(TAG, "favors: $it")
            mAdpater?.updataList(it)
        })
    }
}