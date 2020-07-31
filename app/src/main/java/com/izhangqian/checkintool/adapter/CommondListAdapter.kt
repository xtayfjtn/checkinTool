package com.izhangqian.checkintool.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.bean.checkin.CheckinCommond
import java.lang.ref.WeakReference

class CommondListAdapter(context: Context) : RecyclerView.Adapter<CommondListAdapter.CommondViewHolder>() {

    constructor(context: Context, list: MutableList<CheckinCommond>?) : this(context) {
        if (list != null) {
            mCmdList = list
        }
    }
    var mContext = context
    var mCmdList = mutableListOf<CheckinCommond>()
    var mListener : AdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommondViewHolder {

        var view = LayoutInflater.from(mContext).inflate(R.layout.cmd_item_detail, parent, false)
        return CommondViewHolder(view, this)
    }

    override fun getItemCount(): Int {
        return mCmdList.size
    }

    override fun onBindViewHolder(holder: CommondViewHolder, position: Int) {
        holder.bindView(mCmdList[position])
    }

    fun setAdapterEvent(adapterListener: AdapterListener) {
        mListener = adapterListener
    }

    fun updateData(mutableList: MutableList<CheckinCommond>) {
        mCmdList = mutableList
        notifyDataSetChanged()
    }

    fun removeData(checkinCommond: CheckinCommond) {
        mCmdList.remove(checkinCommond)
        notifyDataSetChanged()
        mListener?.updateDatabase()
    }

    class CommondViewHolder(itemView: View, adapter : CommondListAdapter? = null) : RecyclerView.ViewHolder(itemView) {

        val cmdSpinner = itemView.findViewById<Spinner>(R.id.cmd_step_type_select)
        val idEt = itemView.findViewById<EditText>(R.id.cmd_node_id_et)
        val textEt = itemView.findViewById<EditText>(R.id.cmd_node_text_et)
        val descEt = itemView.findViewById<EditText>(R.id.cmd_node_desc_et)
        val viewType = itemView.findViewById<EditText>(R.id.cmd_node_viewtype_et)
        val posX = itemView.findViewById<EditText>(R.id.cmd_node_positionx_et)
        val posY = itemView.findViewById<EditText>(R.id.cmd_node_positiony_et)
        val delete = itemView.findViewById<Button>(R.id.cmd_delete_btn)
        var mCmdList : WeakReference<CommondListAdapter>? = null
        init {
            mCmdList = WeakReference<CommondListAdapter>(adapter) ?: null
        }
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
            idEt.addTextChangedListener(object : MyTextWatch() {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdViewId = s.toString();
                }
            })
            textEt.setText(checkinCommond.cmdText)
            textEt.addTextChangedListener(object : MyTextWatch() {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdText = s.toString()
                }
            })
            descEt.setText(checkinCommond.cmdDesc)
            descEt.addTextChangedListener(object : MyTextWatch() {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdDesc = s.toString()
                }
            })
            viewType.setText(checkinCommond.cmdViewType)
            viewType.addTextChangedListener(object : MyTextWatch() {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdViewType = s.toString()
                }
            })
            posX.setText(checkinCommond.cmdPositionX)
            posX.addTextChangedListener(object : MyTextWatch() {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdPositionX = s.toString()
                }
            })
            posY.setText(checkinCommond.cmdPositionY)
            posY.addTextChangedListener(object : MyTextWatch() {
                override fun afterTextChanged(s: Editable?) {
                    checkinCommond.cmdPositionY = s.toString()
                }
            })
            delete.setOnClickListener {
                mCmdList?.get()?.removeData(checkinCommond)
            }
        }
    }

    open class MyTextWatch : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
//            TODO("Not yet implemented")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            TODO("Not yet implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            TODO("Not yet implemented")
        }

    }

    interface AdapterListener {
        fun updateDatabase()
    }
}