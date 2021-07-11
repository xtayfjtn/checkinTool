package com.izhangqian.checkintool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.izhangqian.checkintool.bean.checkin.CheckinMainBean
import com.izhangqian.checkintool.sqlite.CheckinItemDbManager
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CommonDetailViewModel(application: Application) : AndroidViewModel(application) {

    fun updateCommon(checkinMainBean: CheckinMainBean) {
        Observable.create(ObservableOnSubscribe<Boolean> { CheckinItemDbManager.instance.insertorUpdateCheckinItem(checkinMainBean) }).subscribeOn(Schedulers.io()).subscribe(Consumer {

        })
    }
}