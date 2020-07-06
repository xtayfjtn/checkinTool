package com.izhangqian.checkintool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.izhangqian.checkintool.bean.HomeItemBean
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class CheckChooseViewModel(application: Application) : AndroidViewModel(application) {
    val mCheckinMainBean : MutableLiveData<MutableList<CheckinMainBean>> = MutableLiveData()
    fun getCheckInData() {
        var disposable = Observable.create(object : ObservableOnSubscribe<MutableList<CheckinMainBean>> {
            override fun subscribe(e: ObservableEmitter<MutableList<CheckinMainBean>>?) {
                var mutableList = mutableListOf<CheckinMainBean>()
                var checkinMainBean = CheckinMainBean()
                mutableList.add(checkinMainBean)
                e?.onNext(mutableList)
            }
        }).subscribe(Consumer {
            mCheckinMainBean.postValue(it)
        })
    }
}