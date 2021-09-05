package com.izhangqian.checkintool.functions.webload

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izhangqian.checkintool.R
import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.utils.Logit

class FavorAdapter(var context: Context) : RecyclerView.Adapter<FavorAdapter.FavorViewHolder>() {

    private val mFavorList = mutableListOf<FavorArticle>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorViewHolder {
        val view = View.inflate(context, R.layout.favor_rv_item, null)
        Logit.i("FavorAdapter", "create adatper")
        return FavorViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavorViewHolder, position: Int) {
        Logit.i("FavorAdapter", "holder: $position")
        if (position >= mFavorList.size) {
            holder.onBindView(mFavorList[mFavorList.size - 1])
        } else {
            holder.onBindView(mFavorList[position])
        }
    }

    fun updataList(list : MutableList<FavorArticle>) {
        Logit.i("Adapter", "update list")
        mFavorList.clear()
        mFavorList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mFavorList.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    open class FavorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTv = itemView.findViewById<TextView>(R.id.favor_url_name)
        private val layout = itemView.findViewById<ConstraintLayout>(R.id.favor_layout_cl)
        private val icon = itemView.findViewById<ImageView>(R.id.favor_item_icon)
        open fun onBindView(favorArticle: FavorArticle) {
            Logit.i("FavorHolder", favorArticle.toString())
            titleTv.text = favorArticle.title
            layout.setOnClickListener {
                val intent = Intent(itemView.context, WebLoadActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(WebLoadActivity.WEB_URL, favorArticle.url)
                itemView.context.startActivity(intent)
            }
            Glide.with(itemView.context).load(favorArticle.imgurl).error(R.mipmap.new_icon).into(icon)
        }
    }
}