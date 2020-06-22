package com.izhangqian.checkintool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izhangqian.checkintool.bean.HomeItemBean
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer

class MainViewModel : ViewModel() {
    var mHomeData : MutableLiveData<List<HomeItemBean>> = MutableLiveData()
    fun getHomeDatas() {
        var disposable = Observable.create(ObservableOnSubscribe<List<HomeItemBean>> {
                var items : MutableList<HomeItemBean> = mutableListOf()
                items.add(HomeItemBean(1))
                items.add(HomeItemBean(1))
                it.onNext(items)
        }).subscribe(object : Consumer<List<HomeItemBean>?> {
            override fun accept(t: List<HomeItemBean>?) {
                mHomeData.postValue(t)
            }
        })
    }
}