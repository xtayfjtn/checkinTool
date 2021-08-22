package com.izhangqian.checkintool.functions.webload

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.utils.Logit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_favor_actiity.*

class WebFavorActivity : AppCompatActivity() {
    private var mFavorModel : FavorViewModel? = null
    private var mAdpater : FavorAdapter? = null
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_favor_actiity)

        mAdpater = FavorAdapter(this)
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = OrientationHelper.VERTICAL
        favor_url_rv.layoutManager = linearLayoutManager
        favor_url_rv.adapter = mAdpater

        mFavorModel = ViewModelProvider(this).get(FavorViewModel::class.java)

        mFavorModel!!.getWebFavors()

        mFavorModel!!.mFavorList?.observe(this, Observer {
            Logit.i("FavorActivity", "favors: $it")
            mAdpater?.updataList(it)
        })
    }
}