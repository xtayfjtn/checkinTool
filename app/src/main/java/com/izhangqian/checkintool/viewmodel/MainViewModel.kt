package com.izhangqian.checkintool.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.izhangqian.checkintool.newdb.FunctionDao
import com.izhangqian.checkintool.newdb.FunctionItemBean
import com.izhangqian.checkintool.newdb.FuntionDb
import com.izhangqian.checkintool.utils.Logit
import com.izhangqian.checkintool.webrequest.api.ControlGetRep
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val TAG = "MainViewModel"
    }

    var mHomeFuntion : LiveData<MutableList<FunctionItemBean>>? = null
    var mFunctionDao : FunctionDao? = null
    init {
        mFunctionDao = FuntionDb.getInstance(application).getFunctionDao()
        mHomeFuntion = mFunctionDao!!.getAllFuntion()
    }

    fun insertOneFunction(context: Context, functionItemBean: FunctionItemBean) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = FuntionDb.getInstance(context).getFunctionDao()!!.insertOneItem(functionItemBean)
            Logit.e(TAG, "insert result $result")
        }
    }

    fun getOnlineFunctions() {
        val dispatcher = ControlGetRep.getControlFunction().subscribeOn(Schedulers.io()).subscribe({
            Logit.i(TAG, "it it: $it")
            viewModelScope.launch(Dispatchers.IO) {
                for (bean in it) {
                    mFunctionDao?.insertOneItem(bean)
                }
            }
        }, {
            Logit.e(TAG, "here is get error: $it")
        })
    }
}