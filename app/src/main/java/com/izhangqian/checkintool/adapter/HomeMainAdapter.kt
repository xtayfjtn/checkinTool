package com.izhangqian.checkintool.adapter

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.adapter.HomeMainAdapter.HomeViewHolder
import com.izhangqian.checkintool.newdb.FunctionItemBean
import com.izhangqian.checkintool.utils.Logit
import kotlinx.android.synthetic.main.layout_home_recycler_item.view.*

open class HomeMainAdapter : RecyclerView.Adapter<HomeViewHolder?> {
    private var mFuntionItems : MutableList<FunctionItemBean> = mutableListOf()
    private var mContext : Context? = null

    constructor(context: Context) {
        mContext = context
    }
    constructor(context: Context, items: List<FunctionItemBean>?) {
        mFuntionItems = mutableListOf()
        if (items != null) {
            mFuntionItems.addAll(items)
        }
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
//        return if (viewType == HomeItemBean.VIEW_TYPE_PDD) {
//            var view = View.inflate(mContext, PddViewHolder.layout, null)
//            var homeViewHolder = PddViewHolder(view)
//            homeViewHolder
//        } else {
            var view = View.inflate(mContext, R.layout.layout_home_recycler_item, null)
            var homeViewHolder = HomeViewHolder(view)
            return homeViewHolder
//        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBindView(mFuntionItems[position])
    }

    override fun getItemCount(): Int {
        return mFuntionItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    fun updataList(list : MutableList<FunctionItemBean>) {
        mFuntionItems.clear()
        mFuntionItems.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(homeItemBean: FunctionItemBean) {
        mFuntionItems.add(homeItemBean)
        notifyDataSetChanged()
    }

    open class HomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var title : TextView? = itemView?.findViewById(R.id.home_item_tv)
        var pddLayout : ConstraintLayout? = itemView?.home_item_layout
        open fun onBindView(homeItemBean: FunctionItemBean) {
            title?.text = homeItemBean.name
            Logit.i("HomeView", "here is bind view")
            pddLayout?.setOnClickListener {
                val target = homeItemBean.className
                val action = homeItemBean.action
                Logit.e("HomeView", "target: $target")
                val context = itemView.context
                val intent = Intent()
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (!TextUtils.isEmpty(action)) {
                    intent.action = action
                } else if (!TextUtils.isEmpty(target)) {
                    intent.setClassName(context, target)
                }
                context.startActivity(intent)
            }
        }
    }
}