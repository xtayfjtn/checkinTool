package com.izhangqian.checkintool.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean

class CheckinListAdapter(context: Context) : RecyclerView.Adapter<CheckinListAdapter.CheckinViewHolder>() {

    var mCheckList = mutableListOf<CheckinMainBean>()
    var mContext : Context? = null
    init {
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckinViewHolder {
        var view = View.inflate(mContext, CheckinViewHolder.layout, null)
        return CheckinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCheckList.size
    }

    override fun onBindViewHolder(holder: CheckinViewHolder, position: Int) {
        holder.bindView(mCheckList[position])
    }

    fun updateData(mutableList: MutableList<CheckinMainBean>) {
        mCheckList.addAll(mutableList)
        notifyDataSetChanged()
    }

    class CheckinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            val layout = R.layout.check_normal_item
        }

        fun bindView(checkinMainBean: CheckinMainBean) {

        }
    }
}