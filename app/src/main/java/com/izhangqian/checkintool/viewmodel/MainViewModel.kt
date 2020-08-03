package com.izhangqian.checkintool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izhangqian.checkintool.bean.HomeItemBean
import com.izhangqian.checkintool.sqlite.HomeItemDbManager
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer

class MainViewModel : ViewModel() {
    var mHomeData : MutableLiveData<MutableList<HomeItemBean>> = MutableLiveData()
    fun getHomeDatas() {
        var disposable = Observable.create(ObservableOnSubscribe<MutableList<HomeItemBean>> {
            var items = HomeItemDbManager.instance.getHomeItems()
            it.onNext(items)
        }).subscribe(object : Consumer<MutableList<HomeItemBean>?> {
            override fun accept(t: MutableList<HomeItemBean>?) {
                mHomeData.postValue(t)
            }
        })
    }
}