package com.izhangqian.checkintool.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.adapter.HomeMainAdapter.HomeViewHolder
import com.izhangqian.checkintool.bean.HomeItemBean
import com.izhangqian.checkintool.viewholder.PddViewHolder

open class HomeMainAdapter : RecyclerView.Adapter<HomeViewHolder?> {
    private var mHomeItems : MutableList<HomeItemBean> = mutableListOf()
    private var mContext : Context? = null

    constructor(context: Context) {
        mContext = context
    }
    constructor(context: Context, items: List<HomeItemBean>?) {
        mHomeItems = mutableListOf()
        if (items != null) {
            mHomeItems.addAll(items)
        }
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return if (viewType == HomeItemBean.VIEW_TYPE_PDD) {
            var view = View.inflate(mContext, PddViewHolder.layout, null)
            var homeViewHolder = PddViewHolder(view)
            homeViewHolder
        } else {
            var view = View.inflate(mContext, R.layout.layout_home_recycler_item, null)
            var homeViewHolder = HomeViewHolder(view)
            homeViewHolder
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBindView(mHomeItems[position])
    }

    override fun getItemCount(): Int {
        return mHomeItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return mHomeItems.get(position).itemType
    }

    fun updataList(list : MutableList<HomeItemBean>) {
        mHomeItems.clear()
        mHomeItems.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(homeItemBean: HomeItemBean) {
        mHomeItems.add(homeItemBean)
        notifyDataSetChanged()
    }

    open class HomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        open fun onBindView(homeItemBean: HomeItemBean) {

        }
    }
}