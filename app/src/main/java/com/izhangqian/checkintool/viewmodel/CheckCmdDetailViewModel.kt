package com.izhangqian.checkintool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.sqlite.CheckinItemDbManager
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CheckCmdDetailViewModel(application: Application) : AndroidViewModel(application) {
    val mCheckCmdDetal = MutableLiveData<CheckinMainBean>()

    fun getCheckCmdDetailByid(id : String) {
        Observable.create<CheckinMainBean> {
            it.onNext(CheckinItemDbManager.instance.getCommondbyId(id))
        }.subscribeOn(Schedulers.io()).subscribe(Consumer {
            mCheckCmdDetal.postValue(it)
        })
    }
}