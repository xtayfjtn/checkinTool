package com.izhangqian.checkintool.functions.webload

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.izhangqian.checkintool.functions.webload.beans.FavorArticle
import com.izhangqian.checkintool.functions.webload.beans.FavorDao
import com.izhangqian.checkintool.newdb.FuntionDb
import com.izhangqian.checkintool.utils.Logit
import com.izhangqian.checkintool.webrequest.api.ControlGetRep
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavorViewModel(application: Application) : AndroidViewModel(application) {

    var mFavorList : LiveData<MutableList<FavorArticle>>? = null
    var mZLFavorList : LiveData<MutableList<FavorArticle>>? = null
    var mFavorDao : FavorDao? = null

    init {
        mFavorDao = FuntionDb.getInstance(application).getFavorDao()
        mFavorList = mFavorDao?.getAllFavors()
        mZLFavorList = mFavorDao?.getZLFavors()
    }
    fun getWebFavors() {
        val disposable = ControlGetRep.getFavors().subscribeOn(Schedulers.io()).subscribe({
            viewModelScope.launch(Dispatchers.IO) {
                for (article in it) {
                    Logit.i("favor", article.toString())
                    if (TextUtils.isEmpty(article.owner)) {
                        article.owner = "zq"
                    }
                    mFavorDao?.insertOneFavor(article)
                }
            }
        }, {
            Logit.e("model", "here is error: ${it.stackTrace}")
        })
    }

    fun getZLWebFavor() {
        val disposable = ControlGetRep.getZLFavors().subscribeOn(Schedulers.io()).subscribe(Consumer {
            viewModelScope.launch(Dispatchers.IO) {
                for (article in it) {
                    Logit.i("favor", article.toString())
                    article.owner = "zl"
                    mFavorDao?.insertOneFavor(article)
                }
            }
        }, {
            Logit.e(TAG, "get zl model error")
        })
    }

    companion object {
        const val TAG = "FavorModel"
    }
}