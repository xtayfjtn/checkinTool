package com.izhangqian.checkintool.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.bean.checkin.CheckinCommond
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean

class CommondListAdapter(context: Context) : RecyclerView.Adapter<CommondListAdapter.CommondViewHolder>() {

    constructor(context: Context, list: MutableList<CheckinCommond>?) : this(context) {
        if (list != null) {
            mCmdList = list
        }
    }
    var mContext = context
    var mCmdList = mutableListOf<CheckinCommond>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommondViewHolder {

        var view = LayoutInflater.from(mContext).inflate(R.layout.cmd_item_detail, parent, false)
        return CommondViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCmdList.size
    }

    override fun onBindViewHolder(holder: CommondViewHolder, position: Int) {
        holder.bindView(mCmdList[position])
    }

    fun updateData(mutableList: MutableList<CheckinCommond>) {
        mCmdList = mutableList
        notifyDataSetChanged()
    }

    class CommondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cmdSpinner = itemView.findViewById<Spinner>(R.id.cmd_step_type_select)
        val idEt = itemView.findViewById<EditText>(R.id.cmd_node_id_et)
        val textEt = itemView.findViewById<EditText>(R.id.cmd_node_text_et)
        fun bindView(checkinCommond: CheckinCommond) {
            cmdSpinner.setSelection(checkinCommond.cmdType - 1)
            cmdSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    checkinCommond.cmdType = position + 1
                }
            }

            idEt.setText(checkinCommond.cmdViewId)
            idEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdViewId = s.toString();
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    TODO("Not yet implemented")
                }
            })
            textEt.setText(checkinCommond.cmdText)
            textEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdText = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    TODO("Not yet implemented")
                }
            })
        }
    }
}