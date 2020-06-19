package com.izhangqian.checkintool.viewholder

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.izhangqian.checkintool.AccessibilityActivity
import com.izhangqian.checkintool.MyApplication
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.adapter.HomeMainAdapter
import com.izhangqian.checkintool.bean.HomeItemBean
import kotlinx.android.synthetic.main.layout_home_item_pdd.view.*

class PddViewHolder(itemView: View?) : HomeMainAdapter.HomeViewHolder(itemView) {
    var title : TextView? = itemView?.findViewById(R.id.home_pdd_item_tv)
    var pddLayout : ConstraintLayout? = itemView?.layout_pdd
    companion object {
        var layout = R.layout.layout_home_item_pdd
    }

    override fun onBindView(homeItemBean: HomeItemBean) {
        super.onBindView(homeItemBean)
        pddLayout?.setOnClickListener {
            var intent = Intent(MyApplication.application, AccessibilityActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            MyApplication.application?.startActivity(intent)
        }
    }
}