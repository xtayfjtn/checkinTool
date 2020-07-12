package com.izhangqian.checkintool.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.izhangqian.checkintool.MyApplication
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.actors.NormalActor
import com.izhangqian.checkintool.actors.PDDActor
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.service.MyService
import com.izhangqian.checkintool.utils.Constants

class CheckinListAdapter(context: Context) : RecyclerView.Adapter<CheckinListAdapter.CheckinViewHolder>() {

    var mCheckList = mutableListOf<CheckinMainBean>()
    var mContext : Context? = null
    init {
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckinViewHolder {
        var view = LayoutInflater.from(mContext).inflate(CheckinViewHolder.layout, parent, false)
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
        var title = itemView.findViewById<TextView>(R.id.check_item_tv)
        var check_run_btn = itemView.findViewById<Button>(R.id.check_run_btn)
        var check_edit_detail = itemView.findViewById<Button>(R.id.check_edit_detail)
        var id : String = ""
        companion object {
            val layout = R.layout.check_normal_item
        }

        fun bindView(checkinMainBean: CheckinMainBean) {
            title.text = checkinMainBean.cmdName
            id = checkinMainBean.cmdId
            check_edit_detail.setOnClickListener {
                // 跳转至详情页
                var intent = Intent()
                intent.action = "com.izhangqian.action.CMD_DETAIL"
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(Constants.EXTRA_CMD_BEAN, checkinMainBean)
                MyApplication.application?.startActivity(intent)
            }
            check_run_btn.setOnClickListener {

//                var clickActor = PDDActor(MyService.getListener())
//                clickActor.handleAction("111")
                var clickActor = NormalActor(MyService.getListener())
                clickActor.handleAction(checkinMainBean)
            }
        }
    }
}