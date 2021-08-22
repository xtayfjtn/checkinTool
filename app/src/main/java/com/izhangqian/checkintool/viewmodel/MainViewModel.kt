package com.izhangqian.checkintool.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.izhangqian.checkintool.newdb.FunctionItemBean
import com.izhangqian.checkintool.newdb.FuntionDb
import com.izhangqian.checkintool.utils.Logit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val TAG = "MainViewModel"
    }

    var mHomeFuntion : LiveData<MutableList<FunctionItemBean>>? = null
    init {
        mHomeFuntion = FuntionDb.getInstance(application).getFunctionDao()!!.getAllFuntion()
    }

    fun insertOneFunction(context: Context, functionItemBean: FunctionItemBean) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = FuntionDb.getInstance(context).getFunctionDao()!!.insertOneItem(functionItemBean)
            Logit.e(TAG, "insert result $result")
        }
    }
}